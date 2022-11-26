package com.taraskrasitsky.ebay.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider {
    FileInputStream propertiesFile;
    Properties properties;

    public PropertiesProvider() {
        try {
            this.propertiesFile = new FileInputStream("src/test/resources/system.properties");
            properties = new Properties();
            properties.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getChromeBrowser() {
        return properties.getProperty("chromeWebDriverPath");
    }

    public String getFireFoxBrowser() {
        return properties.getProperty("firefoxWebDriverPath");
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
}
