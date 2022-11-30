package com.taraskrasitskyi.ebay.utils;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class PropertiesProvider {
    public String getBrowserProperty() {
        return System.getProperty("browser", "chrome");
    }

    public String getBrowserSizeProperty() {
        return System.getProperty("size", "1366x768");
    }

    public static long getTimeoutProperty() {
        String defaultTimeout = String.valueOf(Duration.ofMinutes(2).toMillis());
        String systemTimeout = System.getProperty("timeout", defaultTimeout);
        return Long.parseLong(systemTimeout);
    }

    public static long getPageLoadTimeout() {
        var systemPageLoadTimeout = System.getProperty("pageLoadTimeout", String.valueOf(3 * 60 * 1000));
        return Long.parseLong(systemPageLoadTimeout);
    }
}
