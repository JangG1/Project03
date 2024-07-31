package com.FT.app.Login.API;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }

            // load a properties file from class path, inside static method
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getKakaoSID() {
        return getProperty("kakao.client.id");
    }

    public String getNaverSID() {
        return getProperty("naver.client.id");
    }

    public String getNaverSPW() {
        return getProperty("naver.client.pw");
    }
    
    public String getExIP() {
        return getProperty("ex.ip");
    }
}
