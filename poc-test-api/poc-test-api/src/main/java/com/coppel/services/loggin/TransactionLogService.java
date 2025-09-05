package com.coppel.services.loggin;

import com.coppel.dto.logging.AdditionalInfo;

public interface TransactionLogService {

    void logTransaction(String level, String logType, String status,
                        String message, String hostname,
                        String eventType,
                        String destinationIP, AdditionalInfo additionalInfo);
}
