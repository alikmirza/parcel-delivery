package com.mscourier.controller;

import com.mscourier.dto.request.AlterCourierStatusRequestDto;
import com.mscourier.dto.request.CreateCourierRequest;
import com.mscourier.service.CourierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Courier Services")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @ApiOperation(value = "Service for creating courier")
    @PostMapping("courier/create")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN')")
    public ResponseEntity<?> signUp(@RequestHeader("Authorization") String authToken,
                                    @RequestBody @Valid CreateCourierRequest request) {
        courierService.create(request, authToken);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Service for altering courier status")
    @PutMapping("alter/courier/status")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN','COURIER')")
    public ResponseEntity<?> alterCourierStatus(@RequestHeader("Authorization") String authToken,
                                                @RequestBody @Valid AlterCourierStatusRequestDto request) {
        courierService.alterCourierState(request);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Service for getting all couriers")
    @GetMapping("couriers")
    @PreAuthorize("@authServiceImpl.checkAccess(#authToken,'ADMIN')")
    public ResponseEntity<?> getAllCouriers(@RequestHeader("Authorization") String authToken) {
        return ResponseEntity.ok(courierService.getAll());
    }
}
