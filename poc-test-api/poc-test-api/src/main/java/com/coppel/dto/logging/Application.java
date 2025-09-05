package com.coppel.dto.logging;

import lombok.Data;

public @Data class Application {
    private String name;
    private String version;
    private String env;
    private String kind;
}
