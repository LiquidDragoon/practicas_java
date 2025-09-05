package com.coppel.services.aop;

import com.coppel.component.MeasurementHolder;
import com.coppel.dto.logging.Measurement;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeasurementAspect {

    @Autowired
    private MeasurementHolder measurementHolder;

    @Around("execution(* com.coppel.controllers..*(..))") // Ajusta el paquete
    public Object measureMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Measurement measurement = new Measurement();
        measurement.setMethod(joinPoint.getSignature().getName());

        long startTime = System.currentTimeMillis();
        measurementHolder.setMeasurement(measurement); // Almacena el Measurement en ThreadLocal

        Object result = joinPoint.proceed(); // Ejecuta el método original

        long elapsedTime = System.currentTimeMillis() - startTime;
        measurement.setElapsedTime(elapsedTime);

        measurementHolder.clear(); // Limpia el ThreadLocal

        return result; // Devuelve el resultado del método original
    }
}
