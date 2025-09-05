package com.coppel.constants;

public enum ServiceConstants {
    WEB("Servicio Web"),
    API("API REST"),
    DATABASE("Servicio de Base de Datos"),
    FILE_STORAGE("Almacenamiento de Archivos"),
    CACHE("Servicio de Caché"),
    MESSAGE_QUEUE("Cola de Mensajes"),
    AUTHENTICATION("Servicio de Autenticación"),
    MONITORING("Servicio de Monitoreo"),
    MESSAGE_FACTURAS("Obtener facturas");

    private final String descripcion;

    ServiceConstants(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
