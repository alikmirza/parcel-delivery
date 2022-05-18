package com.mscourier.entity;

import com.mscourier.enums.CourierState;
import com.mscourier.enums.CourierVehicle;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "couriers")
@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "vehicle")
    @Enumerated(EnumType.STRING)
    private CourierVehicle courierVehicle;
    @Column(name = "courier_state")
    private CourierState courierState;

    public Courier() {
    }

    public Courier(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.courierVehicle = builder.courierVehicle;
        this.courierState = builder.courierState;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public CourierVehicle getCourierVehicle() {
        return courierVehicle;
    }

    public CourierState getCourierState() {
        return courierState;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private CourierVehicle courierVehicle;
        private CourierState courierState;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder setCourierVehicle(CourierVehicle courierVehicle) {
            this.courierVehicle = courierVehicle;
            return this;
        }

        public Builder setCourierState(CourierState courierState) {
            this.courierState = courierState;
            return this;
        }

        public Courier build() {
            return new Courier(this);
        }

        public static Builder of(Courier courier) {
            return new Builder()
                    .setId(courier.getId())
                    .setCreatedAt(courier.getCreatedAt())
                    .setUpdatedAt(courier.getUpdatedAt())
                    .setUsername(courier.getUsername())
                    .setCourierState(courier.courierState)
                    .setCourierVehicle(courier.getCourierVehicle());
        }


    }

}
