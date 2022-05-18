package com.msdelivery.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.msdelivery.enums.OrderStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderResponseDto implements Serializable {

    private Long id;
    private String name;
    private String pnrCode;
    private String description;
    private String destination;
    private OrderStatus status;
    private Double weight;
    private BigDecimal amount;
    private String customerUsername;
    private String courierUsername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private Boolean active;


    public Long getId() {
        return id;
    }

    public OrderResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPnrCode() {
        return pnrCode;
    }

    public OrderResponseDto setPnrCode(String pnrCode) {
        this.pnrCode = pnrCode;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderResponseDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public OrderResponseDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderResponseDto setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public OrderResponseDto setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderResponseDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public OrderResponseDto setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
        return this;
    }

    public String getCourierUsername() {
        return courierUsername;
    }

    public OrderResponseDto setCourierUsername(String courierUsername) {
        this.courierUsername = courierUsername;
        return this;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public OrderResponseDto setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public OrderResponseDto setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
