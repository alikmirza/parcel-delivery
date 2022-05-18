package com.msdelivery.dto.response;

import com.msdelivery.enums.ParcelStatus;

import javax.validation.constraints.NotNull;

public class ParcelStateEventResponseDto {
    @NotNull(message = "Parcel id cannot be null!")
    private Long Id;
    @NotNull(message = "Courier username cannot be null!")
    private String courierUsername;
    @NotNull(message = "Parcel status cannot be null!")
    private ParcelStatus parcelStatus;

    public ParcelStateEventResponseDto() {
    }


    public Long getId() {
        return Id;
    }

    public ParcelStateEventResponseDto setId(Long id) {
        this.Id = id;
        return this;
    }

    public String getCourierUsername() {
        return courierUsername;
    }

    public ParcelStateEventResponseDto setCourierUsername(String courierUsername) {
        this.courierUsername = courierUsername;
        return this;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public ParcelStateEventResponseDto setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
        return this;
    }
}
