package com.msdelivery.mq;

import com.msdelivery.config.RabbitMQConfig;
import com.msdelivery.dto.request.OrderStateEventRequestDto;
import com.msdelivery.dto.response.ParcelStateEventResponseDto;
import com.msdelivery.service.ParcelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class RabbitMQConsumer {

    private final ParcelService parcelService;

    public RabbitMQConsumer(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @RabbitListener(queues = RabbitMQConfig.PARCEL_STATUS_QUEUE)
    public void consumeMessageFromQueue(ParcelStateEventResponseDto parcelStateEventResponseDto) {
        parcelService.changeStatus(parcelStateEventResponseDto);

    }

    @RabbitListener(queues = RabbitMQConfig.PARCEL_CREATE_QUEUE)
    public void consumeMessageFromQueueForCreate(OrderStateEventRequestDto orderStateEventRequestDto) {
        parcelService.create(orderStateEventRequestDto);

    }

}
