package com.msdelivery;

import com.msdelivery.dto.request.AlterOrderDestinationRequestDto;
import com.msdelivery.dto.request.AssignCourierRequestDto;
import com.msdelivery.dto.request.CreateOrderRequestDto;
import com.msdelivery.entity.Order;
import com.msdelivery.enums.OrderStatus;
import com.msdelivery.exception.CommonAPIException;
import com.msdelivery.mq.RabbitMQPublisher;
import com.msdelivery.repository.OrderRepository;
import com.msdelivery.security.AuthUser;
import com.msdelivery.service.OrderService;
import com.msdelivery.service.impl.OrderServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MsDeliveryApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Mock
    private RabbitMQPublisher rabbitMQPublisher;
    @Mock
    private AuthUser authUser;

    private OrderService orderService;

    @BeforeAll
    public void init() {
        orderService = new OrderServiceImpl(orderRepository, authUser, rabbitMQPublisher);
        Mockito.when(authUser.getRoles()).thenReturn(Set.of("USER"));
        orderService.create(mockCreateOrderData());
    }

    @Test
    @DisplayName("Test: creating an order")
    @org.junit.jupiter.api.Order(1)
    void testCreateOrder() {
        Order order = mockOrderData(1L);
        Assertions.assertEquals(
                order, orderService.getById(order.getId())
        );
    }

    @Test
    @DisplayName("Test: getting all orders")
    @org.junit.jupiter.api.Order(2)
    void testGetAllOrders() {
        orderService.create(mockCreateOrderData());
        Assertions.assertTrue(
                CollectionUtils.isEqualCollection(mockMultipleOrderData(), orderService.getAll()));
    }

    @Test
    @DisplayName("Test: confirming order")
    @org.junit.jupiter.api.Order(3)
    void testConfirmOrder() {
        orderService.confirm(1L);
        Assertions.assertEquals(
                OrderStatus.ACCEPTED, orderService.getById(1L).getStatus()
        );
    }

    @Test
    @DisplayName("Test: assigning order to the courier")
    @org.junit.jupiter.api.Order(4)
    void testAssignOrderToCourier() {
        AssignCourierRequestDto assignCourierRequestDto = new AssignCourierRequestDto()
                .setId(1L)
                .setCourierUsername("testUsername");
        orderService.assignToCourier(assignCourierRequestDto);
        Assertions.assertEquals(
                assignCourierRequestDto.getCourierUsername(), orderService.getById(1L).getCourierUsername()
        );
    }

    @Test
    @DisplayName("Test: changing order destination")
    @org.junit.jupiter.api.Order(5)
    void testAlterOrderDestination() {
        AlterOrderDestinationRequestDto alterOrderDestinationRequestDto = new AlterOrderDestinationRequestDto()
                .setId(1L)
                .setDestination("Test 2 destination");
        orderService.alterDestination(alterOrderDestinationRequestDto);
        Assertions.assertEquals(
                "Test 2 destination", orderService.getById(1L).getDestination()
        );
    }

    @Test
    @DisplayName("Test: cancelling order")
    @org.junit.jupiter.api.Order(6)
    void testCancelOrder() {
        orderService.cancel(1L);
        Assertions.assertEquals(
                OrderStatus.CANCELED, orderService.getById(1L).getStatus()
        );
    }

    @Test
    @DisplayName("Test: getting order")
    @org.junit.jupiter.api.Order(7)
    void testGetOrder() {
        Assertions.assertNotNull(orderService.getById(1L));
    }

    @Test
    @DisplayName("Test: checking for existence")
    @org.junit.jupiter.api.Order(8)
    void testOrderNotFound() {
        Assertions.assertThrows(
                CommonAPIException.class,
                () -> orderService.getById(3L),
                "Order with 3 id does not exists"
        );
    }

    private CreateOrderRequestDto mockCreateOrderData() {
        return new CreateOrderRequestDto()
                .setName("Jacket")
                .setAmount(BigDecimal.valueOf(20))
                .setWeight(1.0)
                .setDestination("Test street")
                .setDescription("Black jacket");
    }

    private Order mockOrderData(Long id) {
        CreateOrderRequestDto createOrderRequestDto = mockCreateOrderData();
        return Order.builder()
                .setId(id)
                .setName(createOrderRequestDto.getName())
                .setAmount(createOrderRequestDto.getAmount())
                .setWeight(createOrderRequestDto.getWeight())
                .setDestination(createOrderRequestDto.getDestination())
                .setDescription(createOrderRequestDto.getDescription())
                .build();
    }

    private List<Order> mockMultipleOrderData() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(mockOrderData(1L));
        orders.add(mockOrderData(2L));
        return orders;
    }
}
