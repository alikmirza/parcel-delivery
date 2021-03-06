package com.msdelivery.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PARCEL_STATUS_QUEUE = "parcel-status-queue";
    public static final String PARCEL_CREATE_QUEUE = "parcel-create-queue";

    @Bean
    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue orderStatusQueue() {
        return new Queue(PARCEL_STATUS_QUEUE);
    }

    @Bean
    public Queue orderCreateQueue() {
        return new Queue(PARCEL_CREATE_QUEUE);
    }
}