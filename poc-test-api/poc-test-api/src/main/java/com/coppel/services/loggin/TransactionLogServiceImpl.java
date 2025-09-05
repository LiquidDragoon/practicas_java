package com.coppel.services.loggin;

import com.coppel.component.MeasurementHolder;
import com.coppel.constants.ConstantsLogging;
import com.coppel.constants.ServiceConstants;
import com.coppel.dto.logging.*;
import com.coppel.util.PomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionLogServiceImpl.class.getName());

    @Autowired
    private MeasurementHolder measurementHolder;
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_SIZE_BITS = 256;
    private static final int IV_SIZE_BYTES = 12; // 96 bits - recomendado para GCM
    private static final int TAG_BIT_LENGTH = 128; // Tamaño de la etiqueta de autenticación


    /**
     * Registra un log de transacción en formato JSON.
     *
     * @param level        Nivel del log
     * @param logType      Tipo de log
     * @param status       Estado de la transacción
     * @param message      Mensaje del log
     * @param hostname     Nombre del host
     * @param eventType    Tipo de evento
     * @param destinationIP IP de destino
     * @param additionalInfo Información adicional
     */
    public void logTransaction(String level, String logType, String status,
                               String message, String hostname,
                               String eventType,
                               String destinationIP, AdditionalInfo additionalInfo) {

        TransactionLogRequest logRequest = new TransactionLogRequest();

        try{

            final SecretKey secretKey = generateKey();

            //En este campo se coloca la ip del cliente
            //Solo es ejemplo 190.0.0.0
            String ipClient = encrypt("190.0.0.0", secretKey);
            String tracingId = java.util.UUID.randomUUID().toString();

            PomUtils pomUtils = new PomUtils();

            Application application = new Application();
            String schemaVersion =  pomUtils.getVersion();
            application.setName(pomUtils.getName());
            application.setVersion(pomUtils.getVersion());
            application.setEnv(ConstantsLogging.DEV.getDescripcion());
            application.setKind(ServiceConstants.API.getDescripcion());
            Measurement measurement = measurementHolder.getMeasurement();

            // Crear una instancia de TransactionLogRequest
            logRequest.setLevel(level);
            logRequest.setSchemaVersion(schemaVersion);
            logRequest.setLogType(logType);
            logRequest.setSourceIP(ipClient);
            logRequest.setStatus(status);
            logRequest.setMessage(message);
            logRequest.setLogOrigin(ConstantsLogging.ORIGIN_INTERNAL.getDescripcion());
            logRequest.setTimestamp(formattedDate()); // Asignar la fecha y hora actual
            logRequest.setTracingId(tracingId);
            logRequest.setHostname(hostname);
            logRequest.setEventType(eventType);
            logRequest.setApplication(application);
            logRequest.setMeasurement(measurement);
            logRequest.setDestinationIP(destinationIP);
            logRequest.setAdditionalInfo(additionalInfo);


        }catch (Throwable ex){
            logger.error("Error TransactionLogRequest: {}", ex);
        } finally {
            if (secretKey != null) {
                Arrays.fill(secretKey.getEncoded(), (byte) 0);
            }
        }

        logger.info("TransactionLogRequest: {}", logRequest.toString());

    }

    private String formattedDate(){
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("UTC")); // Cambia "UTC" por tu zona horaria si es necesario
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss Z");
        String date = zonedDateTime.format(formatter);
        return date;
    }

    /**
     * Cifra un texto plano utilizando la clave secreta proporcionada.
     * <p>
     * El resultado es una cadena en formato Base64 que contiene el IV y el texto cifrado concatenados.
     * </p>
     *
     * @param plainText El texto plano a cifrar.
     * @param secretKey La clave secreta para el cifrado.
     * @return El texto cifrado en formato Base64.
     * @throws Exception si ocurre un error durante el cifrado.
     */
    public static String encrypt(final String plainText, final SecretKey secretKey) throws Exception {
        final byte[] iv = new byte[IV_SIZE_BYTES];
        new SecureRandom().nextBytes(iv);

        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        final GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_BIT_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        final byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Concatena el IV y el texto cifrado para facilitar el descifrado
        final ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);

        return Base64.getEncoder().encodeToString(byteBuffer.array());
    }

    /**
     * Genera una nueva clave secreta AES de 256 bits de forma segura.
     *
     * @return Una instancia de {@link SecretKey}.
     * @throws Exception si ocurre un error durante la generación de la clave.
     */
    public static SecretKey generateKey() throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE_BITS, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }
}
