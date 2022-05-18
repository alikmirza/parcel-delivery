package com.msdelivery.dto.request;


import com.msdelivery.entity.Order;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OrderStateEventRequestDto implements Serializable {
    @NotNull(message = "Order id cannot be null!")
    private Long Id;

    public Long getParcelId() {
        return Id;
    }

    public OrderStateEventRequestDto setParcelId(Long parcelId) {
        this.Id = parcelId;
        return this;
    }

    public static OrderStateEventRequestDto mapper(Order order) {
        return new OrderStateEventRequestDto()
                .setParcelId(order.getId());
    }

}
