package com.msdelivery.service.impl;

import com.msdelivery.dto.request.OrderStateEventRequestDto;
import com.msdelivery.dto.response.ParcelStateEventResponseDto;
import com.msdelivery.entity.Order;
import com.msdelivery.entity.Parcel;
import com.msdelivery.enums.ParcelStatus;
import com.msdelivery.repository.ParcelRepository;
import com.msdelivery.service.OrderService;
import com.msdelivery.service.ParcelService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.msdelivery.exception.CommonAPIException.throwOnNotFound;


public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final OrderService orderService;

    public ParcelServiceImpl(ParcelRepository parcelRepository, OrderService orderService) {
        this.parcelRepository = parcelRepository;
        this.orderService = orderService;
    }

    @Override
    public void changeStatus(ParcelStateEventResponseDto parcelStateEventResponseDto) {
        saveOrUpdate(
                Parcel.Builder.of(
                        throwOnNotFound(
                                getById(parcelStateEventResponseDto.getId()), parcelStateEventResponseDto.getId()
                        )
                ).setStatus(parcelStateEventResponseDto.getParcelStatus()).build()
        );
    }

    @Override
    public void saveOrUpdate(Parcel parcel) {
        parcelRepository.save(parcel);
    }


    @Override
    public void create(OrderStateEventRequestDto orderStateEventRequestDto) {
        Order order = orderService.getById(orderStateEventRequestDto.getParcelId());
        Parcel parcel = Parcel.builder()
                .setCourierUsername(order.getCourierUsername())
                .setCustomerUsername(order.getCustomerUsername())
                .setStatus(ParcelStatus.CREATED)
                .setParcelSendingTime(LocalDateTime.now())
                .setParcelApproximateAcceptanceTime(LocalDateTime.now().plusMinutes(30L))
                .setLatitude(100 + new Random().nextDouble())
                .setLongitude(100 + new Random().nextDouble())
                .setOrder(order).build();
        saveOrUpdate(parcel);
    }

    @Override
    public Optional<Parcel> getById(Long id) {
        return parcelRepository.findById(id);
    }
}
