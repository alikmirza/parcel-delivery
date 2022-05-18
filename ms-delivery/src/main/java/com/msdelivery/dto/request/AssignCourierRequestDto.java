package com.msdelivery.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class AssignCourierRequestDto implements Serializable {

    @NotNull(message = "Order id cannot be null")
    private Long id;
    @NotEmpty(message = "Courier username cannot be empty")
    private String courierUsername;


    public Long getId() {
        return id;
    }

    public AssignCourierRequestDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCourierUsername() {
        return courierUsername;
    }

    public AssignCourierRequestDto setCourierUsername(String courierUsername) {
        this.courierUsername = courierUsername;
        return this;
    }
}
