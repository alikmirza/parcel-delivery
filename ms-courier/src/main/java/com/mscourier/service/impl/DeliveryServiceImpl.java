package com.mscourier.service.impl;

import com.mscourier.config.ApplicationConfig;
import com.mscourier.dto.response.OrderResponseDto;
import com.mscourier.service.DeliveryService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final RestTemplate restTemplate;

    public DeliveryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderResponseDto getOrderDetailsById(Long orderId) {
        return restTemplate.getForObject(
                ApplicationConfig.getMsDeliveryOrderByIdUrl(),
                OrderResponseDto.class,
                orderId
        );
    }

    @Override
    public List<OrderResponseDto> getOrdersByCourierName(String courierName) {
        return restTemplate.exchange(
                ApplicationConfig.getMsDeliveryOrderByCourierNameUrl(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderResponseDto>>() {
                },
                courierName
        ).getBody();
    }
}
