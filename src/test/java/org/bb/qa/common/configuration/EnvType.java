package org.bb.qa.common.configuration;

public enum EnvType {

    PRODUCTION("production"),
    STAGING("staging"),
    DEV("dev");

    private final String key;

    EnvType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
