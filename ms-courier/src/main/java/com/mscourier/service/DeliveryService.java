package com.mscourier.service;

import com.mscourier.dto.response.OrderResponseDto;

import java.util.List;

public interface DeliveryService {

    OrderResponseDto getOrderDetailsById(Long orderId);
    List<OrderResponseDto> getOrdersByCourierName(String courierName);
}
