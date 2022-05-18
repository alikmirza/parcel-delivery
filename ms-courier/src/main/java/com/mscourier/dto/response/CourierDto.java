package com.mscourier.dto.response;

import com.mscourier.entity.Courier;
import com.mscourier.enums.CourierVehicle;

import java.time.LocalDateTime;

public class CourierDto {

    private Long id;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CourierVehicle courierVehicle;


    public Long getId() {
        return id;
    }

    public CourierDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CourierDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public CourierDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public CourierDto setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CourierVehicle getCourierVehicle() {
        return courierVehicle;
    }

    public CourierDto setCourierVehicle(CourierVehicle courierVehicle) {
        this.courierVehicle = courierVehicle;
        return this;
    }

    public static CourierDto mapper(Courier courier) {
        return new CourierDto()
                .setId(courier.getId())
                .setCreatedAt(courier.getCreatedAt())
                .setUpdatedAt(courier.getUpdatedAt())
                .setUsername(courier.getUsername())
                .setCourierVehicle(courier.getCourierVehicle());
    }
}
