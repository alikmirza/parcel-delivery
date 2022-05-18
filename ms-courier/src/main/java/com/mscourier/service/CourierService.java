package com.mscourier.service;

import com.mscourier.dto.request.AlterCourierStatusRequestDto;
import com.mscourier.dto.request.ParcelStateEventRequestDto;
import com.mscourier.dto.response.CourierDto;
import com.mscourier.dto.request.CreateCourierRequest;
import com.mscourier.entity.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierService {

    void saveOrUpdate(Courier courier);
    void create(CreateCourierRequest request, String bearerToken);
    void alterCourierState(AlterCourierStatusRequestDto requestDto);
    void alterCourierState(ParcelStateEventRequestDto parcelStateEventRequestDto);
    Optional<Courier> getByUsername(String username);
    List<CourierDto> getAll();
}
