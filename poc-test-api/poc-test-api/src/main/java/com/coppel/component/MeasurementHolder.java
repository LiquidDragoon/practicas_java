package com.coppel.component;

import com.coppel.dto.logging.Measurement;
import org.springframework.stereotype.Component;

@Component
public class MeasurementHolder {
    private static final ThreadLocal<Measurement> currentMeasurement = new ThreadLocal<>();

    public void setMeasurement(Measurement measurement) {
        currentMeasurement.set(measurement);
    }

    public Measurement getMeasurement() {
        return currentMeasurement.get();
    }

    public void clear() {
        currentMeasurement.remove();
    }
}


