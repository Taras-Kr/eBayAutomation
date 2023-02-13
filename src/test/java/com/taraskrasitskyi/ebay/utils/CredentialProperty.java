package com.taraskrasitskyi.ebay.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialProperty {
    private Properties credentialProperty;
    private FileInputStream fileInputStream;

    public CredentialProperty() {
        try {
            fileInputStream = new FileInputStream("src/test/resources/credential.properties");
            credentialProperty = new Properties();
            credentialProperty.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValidEmail(){
        return credentialProperty.getProperty("VALID_EMAIL");
    }

    public String getValidPassword(){
        return credentialProperty.getProperty("VALID_PASSWORD");
    }
    public String getInvalidEmail(){
        return credentialProperty.getProperty("INVALID_EMAIL");
    }

    public String getInvalidPassword(){
        return credentialProperty.getProperty("INVALID_PASSWORD");
    }

}
