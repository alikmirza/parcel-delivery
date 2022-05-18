package com.mscourier.controller;

import com.mscourier.dto.request.ParcelStateEventRequestDto;
import com.mscourier.mq.RabbitMQPublisher;
import com.mscourier.service.CourierService;
import com.mscourier.service.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("delivery")
@Api(value = "Delivery Services")
public class DeliveryController {

    private final RabbitMQPublisher rabbitMQPublisher;
    private final DeliveryService deliveryService;
    private final CourierService courierService;

    public DeliveryController(RabbitMQPublisher rabbitMQPublisher,
                              DeliveryService deliveryService,
                              CourierService courierService) {
        this.rabbitMQPublisher = rabbitMQPublisher;
        this.deliveryService = deliveryService;
        this.courierService = courierService;
    }

    @ApiOperation(value = "Service for altering parcel status")
    @PostMapping("alter/status")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN','COURIER')")
    public ResponseEntity<?> alterParcelStatus(@RequestHeader("Authorization") String authToken,
                                               @RequestBody @Valid ParcelStateEventRequestDto parcelStateEventRequestDto) {
        rabbitMQPublisher.publishParcelStatusChange(parcelStateEventRequestDto);
        courierService.alterCourierState(parcelStateEventRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Service for getting order status by id")
    @GetMapping("order/{id}")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN','COURIER')")
    public ResponseEntity<?> getOrderDetailsById(@RequestHeader("Authorization") String authToken,
                                                 @PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.getOrderDetailsById(id));
    }

    @ApiOperation(value = "Service for getting orders by courier username")
    @GetMapping("order/by-courierName/{courierName}")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN','COURIER')")
    public ResponseEntity<?> getOrdersByCourierName(@RequestHeader("Authorization") String authToken,
                                                    @PathVariable String courierName) {
        return ResponseEntity.ok(deliveryService.getOrdersByCourierName(courierName));
    }

}
