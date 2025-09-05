package com.coppel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PomUtils {
    private static final Logger logger = LoggerFactory.getLogger(PomUtils.class);

    private static final String APP_VERSION = "1.0.0";
    private static final String APP_NAME = "poc-test-api-v1";
    private static final String APP_DESCRIPTION = "Plantilla para creación de servicios con Spring Boot";
    private static final String APP_GROUP_ID = "com.coppel";
    private static final String APP_ARTIFACT_ID = "poc-test-api";

    public String getVersion() {
        return APP_VERSION;
    }

    public String getName() {
        return APP_NAME;
    }

    public String getDescription() {
        return APP_DESCRIPTION;
    }

    public String getGroupId() {
        return APP_GROUP_ID;
    }

    public String getArtifactId() {
        return APP_ARTIFACT_ID;
    }
}