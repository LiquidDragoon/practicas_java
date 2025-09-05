package com.coppel.dto.logging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class AdditionalInfo {
    @JsonProperty("clientID")
    private int clientId;
}
