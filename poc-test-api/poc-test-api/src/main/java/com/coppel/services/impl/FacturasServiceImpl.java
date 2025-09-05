package com.coppel.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.coppel.constants.ConstantsLogging;
import com.coppel.constants.ServiceConstants;
import com.coppel.dto.FacturasDTO;
import com.coppel.dto.ActualizarFacturaDTO;
import com.coppel.dto.logging.AdditionalInfo;
import com.coppel.entities.Facturas;
import com.coppel.mappers.FacturasDetalleMapper;
import com.coppel.mappers.FacturasMapper;
import com.coppel.repositories.FacturasRepository;
import com.coppel.services.FacturasService;

import com.coppel.services.loggin.TransactionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * FacturasService
 */
@Service
public class FacturasServiceImpl implements FacturasService {

    @Autowired
    private FacturasRepository repository;
    @Autowired
    private TransactionLogService transactionLogService;
    private static final String MESSAGE_NOT_FOUND = "Factura no existente.";

    @Override
    public List<FacturasDTO> consultarFacturas() {
        final List<FacturasDTO> facturas = new ArrayList<>();
        repository.findAll().forEach(factura -> facturas.add(FacturasMapper.mapFacturas(factura)));
        logTransactionForFacturas(ConstantsLogging.SUCCESS.getDescripcion(),
                ServiceConstants.MESSAGE_FACTURAS.getDescripcion(), null);
        return facturas;
    }

    @Override
    public FacturasDTO consultarFactura(final Long id) {
        final Optional<Facturas> existeFactura = repository.findById(id);
        FacturasDTO facturasDTO = null;
        if (existeFactura.isPresent()) {
            facturasDTO = FacturasMapper.mapFacturas(existeFactura.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NOT_FOUND);
        }
        return facturasDTO;
    }

    @Override
    public FacturasDTO registrarFactura(final FacturasDTO nuevaFactura) {
        final Facturas facturas = FacturasMapper.mapFacturasDTO(nuevaFactura);
        return FacturasMapper.mapFacturas(this.repository.save(facturas));
    }

    @Override
    public ActualizarFacturaDTO actualizarFactura(final Long id, final FacturasDTO actualizacionesFactura) {
        ActualizarFacturaDTO facturaActualizada = null;
        final Optional<Facturas> existeFactura = this.repository.findById(id);
        if (existeFactura.isPresent()) {
            final Facturas facturaActual = existeFactura.get();
            if (facturaActual.getMonto() != actualizacionesFactura.getMonto()) {
                facturaActual.setMonto(actualizacionesFactura.getMonto());
            }
            if (facturaActual.getNitCliente() != actualizacionesFactura.getNitCliente()) {
                facturaActual.setNitCliente(actualizacionesFactura.getNitCliente());
            }
            if (facturaActual.getNombreCliente() != actualizacionesFactura.getNombreCliente()) {
                facturaActual.setNombreCliente(actualizacionesFactura.getNombreCliente());
            }
            facturaActual.setFacturaDetalleList(
                    FacturasDetalleMapper.mapFacturasDetalleDTO(actualizacionesFactura.getFacturaDetalleList()));
            this.repository.save(facturaActual);
            facturaActualizada = FacturasMapper.mapActulizarFacturas(facturaActual);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NOT_FOUND);
        }
        return facturaActualizada;
    }

    @Override
    public FacturasDTO eliminarFactura(final Long id) {
        FacturasDTO facturaEliminada = null;
        final Optional<Facturas> existeFactura = this.repository.findById(id);
        if (existeFactura.isPresent()) {
            facturaEliminada = FacturasMapper.mapFacturas(existeFactura.get());
            this.repository.delete(existeFactura.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NOT_FOUND);
        }
        return facturaEliminada;
    }

    private void logTransactionForFacturas(String status, String message, String clientId) {

        String serverIpAddress = "N/A"; // Valor por defecto

        AdditionalInfo additionalInfo = new AdditionalInfo();
        // Asegúrate de que el ID de cliente sea dinámico o configurable si es posible
        // Opcional: si el clientId es para un contexto específico, debe venir del contexto de seguridad o de la sesión.
        // Campo clientId es el que colocara
        additionalInfo.setClientId(872876);

        transactionLogService.logTransaction(
                ConstantsLogging.INFO.getDescripcion(),
                "CONSULTA_FACTURAS",
                status,
                message,
                "server-1",
                "FACTURA_CONSULTA_RESULTADO",
                serverIpAddress,
                additionalInfo
        );
    }
}
