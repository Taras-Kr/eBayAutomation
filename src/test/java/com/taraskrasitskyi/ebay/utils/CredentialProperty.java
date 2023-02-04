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

    public String getValidEmailOrUserName(){
        return credentialProperty.getProperty("VALID_EMAIL_OR_USER_NAME");
    }

    public String getValidPassword(){
        return credentialProperty.getProperty("VALID_PASSWORD");
    }

}
