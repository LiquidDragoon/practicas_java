package com.coppel.constants;

public enum ConstantsLogging {
    INFO("Información"),
    DEBUG("Depuración"),
    ERROR("Error"),
    WARNING("Advertencia"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    DEV("DEV"),
    QA("QA"),
    PROD("PROD"),
    ORIGIN_INTERNAL("INTERNAL");


    private final String descripcion;

    ConstantsLogging(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
