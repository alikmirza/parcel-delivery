package com.msdelivery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    private static String msAuthValidateUrl;

    public static String getMsAuthValidateUrl() {
        return msAuthValidateUrl;
    }

    @Value("${ms-auth.validate.url}")
    public void setMsAuthValidateUrl(String msAuthValidateUrl) {
        ApplicationConfig.msAuthValidateUrl = msAuthValidateUrl;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }
}
