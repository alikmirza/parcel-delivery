package com.msdelivery.service.impl;

import com.msdelivery.dto.request.AlterOrderDestinationRequestDto;
import com.msdelivery.dto.request.AssignCourierRequestDto;
import com.msdelivery.dto.request.CreateOrderRequestDto;
import com.msdelivery.entity.Order;
import com.msdelivery.enums.OrderStatus;
import com.msdelivery.mq.RabbitMQPublisher;
import com.msdelivery.repository.OrderRepository;
import com.msdelivery.security.AuthUser;
import com.msdelivery.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.msdelivery.dto.request.OrderStateEventRequestDto.mapper;
import static com.msdelivery.enums.UserRoles.ADMIN;
import static com.msdelivery.exception.CommonAPIException.*;
import static com.msdelivery.util.OrderUtil.calculateDeliveryAmount;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AuthUser authUser;
    private final RabbitMQPublisher rabbitMQPublisher;


    public OrderServiceImpl(OrderRepository orderRepository,
                            AuthUser authUser,
                            RabbitMQPublisher rabbitMQPublisher) {
        this.orderRepository = orderRepository;
        this.authUser = authUser;
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    @Override
    public void create(CreateOrderRequestDto requestDto) {
        BigDecimal deliveryAmount = calculateDeliveryAmount(requestDto.getWeight(), requestDto.getAmount());
        Order order = Order.builder()
                .setName(requestDto.getName())
                .setDescription(requestDto.getDescription())
                .setDestination(requestDto.getDestination())
                .setStatus(OrderStatus.CREATED)
                .setWeight(requestDto.getWeight())
                .setAmount(requestDto.getAmount())
                .setDeliveryAmount(deliveryAmount)
                .setTotalAmount(requestDto.getAmount().add(deliveryAmount))
                .setCourierUsername(authUser.getUsername())
                .build();
        saveOrUpdate(order);
    }

    @Override
    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void alterDestination(AlterOrderDestinationRequestDto requestDto) {
        Order order = throwOnNotFound(
                getByIdAndCustomerUsername(requestDto.getId(), authUser.getUsername()), requestDto.getId()
        );
        throwOnStatus(order, OrderStatus.CANCELED.name());
        saveOrUpdate(
                Order.Builder.of(order)
                        .setDestination(requestDto.getDestination())
                        .setUpdatedAt(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public void cancel(Long id) {
        Order order = getById(id);
        throwOnStatus(order, OrderStatus.CANCELED.name());
        saveOrUpdate(
                Order.Builder.of(order)
                        .setStatus(OrderStatus.CANCELED)
                        .setUpdatedAt(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public void confirm(Long id) {
        Order order = throwOnNotFound(
                orderRepository.findById(id), id
        );
        throwOnStatusNegation(order, OrderStatus.CREATED.name());
        saveOrUpdate(
                Order.Builder.of(order)
                        .setStatus(OrderStatus.ACCEPTED)
                        .setUpdatedAt(LocalDateTime.now())
                        .build()
        );
        rabbitMQPublisher.publishParcelCreate(mapper(order));
    }

    @Override
    public void assignToCourier(AssignCourierRequestDto requestDto) {
        Order order = throwOnNotFound(
                orderRepository.findById(requestDto.getId()), requestDto.getId()
        );
        throwOnStatusNegation(order, OrderStatus.ACCEPTED.name());
        saveOrUpdate(
                Order.Builder.of(order)
                        .setCourierUsername(requestDto.getCourierUsername())
                        .setUpdatedAt(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public Order getById(Long id) {
        Optional<Order> booking = authUser.getRoles().contains(ADMIN.name()) ?
                orderRepository.findById(id) :
                getByIdAndCustomerUsername(id, authUser.getUsername());
        return throwOnNotFound(booking, id);
    }

    @Override
    public Optional<Order> getByIdAndCustomerUsername(Long id, String customerUsername) {
        return orderRepository.getByIdAndCustomerUsername(id, customerUsername);
    }

    @Override
    public List<Order> getAll() {
        return authUser.getRoles().contains(ADMIN.name()) ?
                orderRepository.findAll() :
                getAllByUsername(authUser.getUsername());
    }

    @Override
    public List<Order> getAllByUsername(String username) {
        return orderRepository.getAllByCustomerUsername(username);
    }
}
