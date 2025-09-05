package com.coppel.dto.logging;

import lombok.Data;

public @Data class Measurement {
    private String method;
    private long elapsedTime;
}
