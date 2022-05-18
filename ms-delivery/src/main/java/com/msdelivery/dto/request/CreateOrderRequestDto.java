package com.msdelivery.dto.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

public class CreateOrderRequestDto implements Serializable {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    private String description;
    @NotEmpty(message = "Destination cannot be empty")
    private String destination;
    @NotEmpty(message = "Weight cannot be empty")
    private Double weight;
    @NotEmpty(message = "Amount cannot be empty")
    private BigDecimal amount;

    public String getName() {
        return name;
    }

    public CreateOrderRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrderRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public CreateOrderRequestDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public CreateOrderRequestDto setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CreateOrderRequestDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
