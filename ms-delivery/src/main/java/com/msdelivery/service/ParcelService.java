package com.msdelivery.service;

import com.msdelivery.dto.request.OrderStateEventRequestDto;
import com.msdelivery.dto.response.ParcelStateEventResponseDto;
import com.msdelivery.entity.Parcel;

import java.util.Optional;


public interface ParcelService {

    void changeStatus(ParcelStateEventResponseDto parcelStateEventResponseDto);
    void saveOrUpdate(Parcel parcel);
    void create(OrderStateEventRequestDto orderStateEventRequestDto);
    Optional<Parcel> getById(Long id);
}
