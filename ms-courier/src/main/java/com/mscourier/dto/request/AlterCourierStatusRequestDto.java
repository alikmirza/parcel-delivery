package com.mscourier.dto.request;

import com.mscourier.enums.CourierState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AlterCourierStatusRequestDto implements Serializable {

    @NotBlank(message = "Username cannot be empty")
    private String username;
    @NotNull(message = "Courier state cannot be null")
    private CourierState courierState;

    public String getUsername() {
        return username;
    }

    public AlterCourierStatusRequestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public CourierState getCourierState() {
        return courierState;
    }

    public AlterCourierStatusRequestDto setCourierState(CourierState courierState) {
        this.courierState = courierState;
        return this;
    }
}
