package com.msdelivery.entity;


import com.msdelivery.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "creted_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "destination")
    private String destination;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "delivery_amount")
    private BigDecimal deliveryAmount;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "customer_username")
    private String customerUsername;
    @Column(name = "courier_name")
    private String courierUsername;

    @OneToOne(mappedBy = "order")
    private Parcel parcel;

    public Order() {
    }

    public Order(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.description = builder.description;
        this.destination = builder.destination;
        this.status = builder.status;
        this.weight = builder.weight;
        this.amount = builder.amount;
        this.deliveryAmount = builder.deliveryAmount;
        this.totalAmount = builder.totalAmount;
        this.customerUsername = builder.customerUsername;
        this.courierUsername = builder.courierUsername;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Double getWeight() {
        return weight;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public String getCourierUsername() {
        return courierUsername;
    }

    public Parcel getParcel() {
        return parcel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(name, order.name) && Objects.equals(description, order.description) && Objects.equals(destination, order.destination) && Objects.equals(weight, order.weight) && Objects.equals(customerUsername, order.customerUsername) && Objects.equals(courierUsername, order.courierUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, destination, weight, customerUsername, courierUsername);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String name;
        private String description;
        private String destination;
        private OrderStatus status;
        private Double weight;
        private BigDecimal amount;
        private BigDecimal deliveryAmount;
        private BigDecimal totalAmount;
        private String customerUsername;
        private String courierUsername;


        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder setWeight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setDeliveryAmount(BigDecimal deliveryAmount) {
            this.deliveryAmount = deliveryAmount;
            return this;
        }

        public Builder setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setCustomerUsername(String customerUsername) {
            this.customerUsername = customerUsername;
            return this;
        }

        public Builder setCourierUsername(String courierUsername) {
            this.courierUsername = courierUsername;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

        public static Builder of(Order order) {
            return new Builder()
                    .setId(order.getId())
                    .setName(order.getName())
                    .setAmount(order.getAmount())
                    .setDeliveryAmount(order.getDeliveryAmount())
                    .setTotalAmount(order.getTotalAmount())
                    .setCourierUsername(order.getCourierUsername())
                    .setCustomerUsername(order.getCustomerUsername())
                    .setDescription(order.getDescription())
                    .setDestination(order.getDestination())
                    .setStatus(order.getStatus())
                    .setCreatedAt(order.getCreatedAt())
                    .setUpdatedAt(order.getUpdatedAt());
        }
    }
}
