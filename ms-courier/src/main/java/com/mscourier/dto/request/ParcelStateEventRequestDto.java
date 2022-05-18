package com.mscourier.dto.request;

import com.mscourier.enums.ParcelStatus;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ParcelStateEventRequestDto implements Serializable {

    @NotNull(message = "Parcel id cannot be null!")
    private Long parcelId;
    @NotNull(message = "Courier username cannot be null!")
    private String courierUsername;
    @NotNull(message = "Parcel status cannot be null!")
    private ParcelStatus parcelStatus;

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public String getCourierUsername() {
        return courierUsername;
    }

    public void setCourierUsername(String courierUsername) {
        this.courierUsername = courierUsername;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }
}
