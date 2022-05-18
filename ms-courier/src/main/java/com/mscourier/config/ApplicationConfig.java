package com.mscourier.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    private static String msAuthSignUpUrl;
    private static String msDeliveryOrderByIdUrl;
    private static String msDeliveryOrderByCourierNameUrl;
    private static String msAuthValidateUrl;

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

    public static String getMsAuthSignUpUrl() {
        return msAuthSignUpUrl;
    }

    @Value("${ms-auth.sign-up.url}")
    public void setMsAuthSignUpUrl(String msAuthSignUpUrl) {
        ApplicationConfig.msAuthSignUpUrl = msAuthSignUpUrl;
    }

    public static String getMsDeliveryOrderByIdUrl() {
        return msDeliveryOrderByIdUrl;
    }

    @Value("${ms-delivery.order-by-id.url}")
    public void setMsDeliveryOrderByIdUrl(String msDeliveryOrderByIdUrl) {
        ApplicationConfig.msDeliveryOrderByIdUrl = msDeliveryOrderByIdUrl;
    }

    public static String getMsDeliveryOrderByCourierNameUrl() {
        return msDeliveryOrderByCourierNameUrl;
    }

    @Value("${ms-delivery.order-by-courier-name.url}")
    public void setMsDeliveryOrderByCourierNameUrl(String msDeliveryOrderByCourierNameUrl) {
        ApplicationConfig.msDeliveryOrderByCourierNameUrl = msDeliveryOrderByCourierNameUrl;
    }

    public static String getMsAuthValidateUrl() {
        return msAuthValidateUrl;
    }

    @Value("${ms-auth.validate.url}")
    public void setMsAuthValidateUrl(String msAuthValidateUrl) {
        ApplicationConfig.msAuthValidateUrl = msAuthValidateUrl;
    }
}
