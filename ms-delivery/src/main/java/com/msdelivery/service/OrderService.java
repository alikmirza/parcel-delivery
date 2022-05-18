package com.msdelivery.service;

import com.msdelivery.dto.request.AlterOrderDestinationRequestDto;
import com.msdelivery.dto.request.AssignCourierRequestDto;
import com.msdelivery.dto.request.CreateOrderRequestDto;
import com.msdelivery.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void create(CreateOrderRequestDto requestDto);
    void alterDestination(AlterOrderDestinationRequestDto requestDto);
    void cancel(Long id);
    void confirm(Long id);
    void assignToCourier(AssignCourierRequestDto requestDto);
    Order saveOrUpdate(Order order);
    Order getById(Long id);
    Optional<Order> getByIdAndCustomerUsername(Long id, String customerUsername);
    List<Order> getAll();
    List<Order> getAllByUsername(String username);
}
