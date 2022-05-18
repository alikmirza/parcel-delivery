package com.msdelivery.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class AlterOrderDestinationRequestDto implements Serializable {

    @NotNull(message = "Order id cannot be null")
    private Long id;
    @NotBlank(message = "Destination cannot be empty")
    private String destination;


    public Long getId() {
        return id;
    }

    public AlterOrderDestinationRequestDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public AlterOrderDestinationRequestDto setDestination(String destination) {
        this.destination = destination;
        return this;
    }
}
