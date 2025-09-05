package com.coppel.dto.logging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class TransactionLogRequest {
    private String level;
    private String schemaVersion;
    private String logType;
    private String sourceIP;
    private String status;
    private String message;
    private String logOrigin;
    private String timestamp;
    private String tracingId;
    private String hostname;
    private String eventType;
    private Application application;
    private Measurement measurement;
    private String destinationIP;

    @JsonProperty("additionalInfo")
    private AdditionalInfo additionalInfo;

}
