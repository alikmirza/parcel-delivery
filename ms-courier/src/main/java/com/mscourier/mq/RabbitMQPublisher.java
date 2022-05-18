package com.mscourier.mq;

import com.mscourier.config.RabbitMQConfig;
import com.mscourier.dto.request.ParcelStateEventRequestDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishParcelStatusChange(ParcelStateEventRequestDto parcelStateEventRequestDto) {
        rabbitTemplate.convertSendAndReceive(
                RabbitMQConfig.PARCEL_STATUS_QUEUE,
                parcelStateEventRequestDto
        );
    }
}
