package com.msdelivery.mq;

import com.msdelivery.config.RabbitMQConfig;
import com.msdelivery.dto.request.OrderStateEventRequestDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishParcelCreate(OrderStateEventRequestDto orderStateEventRequestDto) {
        rabbitTemplate.convertSendAndReceive(
                RabbitMQConfig.PARCEL_CREATE_QUEUE,
                orderStateEventRequestDto
        );
    }
}
