package com.msdelivery.controller;

import com.msdelivery.dto.request.AlterOrderDestinationRequestDto;
import com.msdelivery.dto.request.AssignCourierRequestDto;
import com.msdelivery.dto.request.CreateOrderRequestDto;
import com.msdelivery.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequestMapping(value = "/order")
@RestController
@Api(value = "Order Services")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Service for creating order")
    @PostMapping("/create")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'USER')")
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderRequestDto requestDto,
                                         @RequestHeader("Authorization")
                                         @NotBlank(message = "Auth token expected") String authToken) {
        orderService.create(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Service for altering order destination")
    @PutMapping("/alter-destination")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'USER')")
    public ResponseEntity<?> alterOrderDestination(@RequestBody @Valid AlterOrderDestinationRequestDto requestDto,
                                                   @RequestHeader("Authorization")
                                                   @NotBlank(message = "Auth token expected") String authToken) {
        orderService.alterDestination(requestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Service for canceling order")
    @PutMapping("/cancel/{id}")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'USER')")
    public ResponseEntity<?> cancelOrder(@PathVariable(value = "id") Long id,
                                         @RequestHeader("Authorization")
                                         @NotBlank(message = "Auth token expected") String authToken) {
        orderService.cancel(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Service for confirming order")
    @PutMapping("/confirm/{id}")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN')")
    public ResponseEntity<?> confirmOrder(@PathVariable(value = "id") Long id,
                                          @RequestHeader("Authorization")
                                          @NotBlank(message = "Auth token expected") String authToken) {
        orderService.confirm(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Service for assigning order to the courier")
    @PutMapping("/assign-courier")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN')")
    public ResponseEntity<?> assingOrderToCourier(@RequestBody @Valid AssignCourierRequestDto requestDto,
                                                  @RequestHeader("Authorization")
                                                  @NotBlank(message = "Auth token expected") String authToken) {
        orderService.assignToCourier(requestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Service for getting order by id")
    @GetMapping("/{id}")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'USER','ADMIN')")
    public ResponseEntity<?> getOrderById(@PathVariable(value = "id") Long id,
                                          @RequestHeader("Authorization")
                                          @NotBlank(message = "Auth token expected") String authToken) {
        return ResponseEntity.ok(orderService.getById(id));
    }


    @ApiOperation(value = "Service for altering all orders")
    @GetMapping()
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'USER','ADMIN')")
    public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization")
                                          @NotBlank(message = "Auth token expected") String authToken) {
        return ResponseEntity.ok(orderService.getAll());
    }

}
