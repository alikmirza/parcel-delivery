package com.mscourier.service.impl;

import com.mscourier.dto.request.AlterCourierStatusRequestDto;
import com.mscourier.dto.request.CreateCourierRequest;
import com.mscourier.dto.request.ParcelStateEventRequestDto;
import com.mscourier.dto.request.SignUpRequestDto;
import com.mscourier.dto.response.CourierDto;
import com.mscourier.entity.Courier;
import com.mscourier.enums.CourierState;
import com.mscourier.enums.ParcelStatus;
import com.mscourier.exception.CommonAPIException;
import com.mscourier.repository.CourierRepository;
import com.mscourier.service.AuthenticationService;
import com.mscourier.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourierServiceImpl implements CourierService {

    private final AuthenticationService authenticationService;
    private final CourierRepository courierRepository;

    public CourierServiceImpl(AuthenticationService authenticationService, CourierRepository courierRepository) {
        this.authenticationService = authenticationService;
        this.courierRepository = courierRepository;
    }

    @Override
    public void saveOrUpdate(Courier courier) {
        courierRepository.save(courier);
    }

    @Override
    @Transactional
    public void create(CreateCourierRequest request, String bearerToken) {
        authenticationService.signUpCourier(SignUpRequestDto.Builder.of(request)
                .setRole("COURIER")
                .build(), bearerToken
        );
        Courier courier = Courier.builder()
                .setUsername(request.getUsername())
                .setCourierVehicle(request.getCourierVehicle())
                .setCourierState(CourierState.INACTIVE)
                .build();
        saveOrUpdate(courier);
    }

    @Override
    public void alterCourierState(AlterCourierStatusRequestDto requestDto) {
        Courier courier = getByUsername(requestDto.getUsername()).orElseThrow(() -> new CommonAPIException(
                HttpStatus.BAD_REQUEST, String.format("Courier with username %s does not exists", requestDto.getUsername()))
        );
        saveOrUpdate(
                Courier.Builder.of(courier)
                        .setCourierState(requestDto.getCourierState())
                        .setUpdatedAt(LocalDateTime.now()).build()
        );
    }

    @Override
    public void alterCourierState(ParcelStateEventRequestDto stateEventRequestDto) {
        AlterCourierStatusRequestDto statusRequestDto =
                new AlterCourierStatusRequestDto().setUsername(stateEventRequestDto.getCourierUsername());
        if (stateEventRequestDto.getParcelStatus().equals(ParcelStatus.DELIVERING))
            alterCourierState(statusRequestDto.setCourierState(CourierState.DELIVERING));
        else if (stateEventRequestDto.getParcelStatus().equals(ParcelStatus.DELIVERED))
            alterCourierState(statusRequestDto.setCourierState(CourierState.ACTIVE));
    }

    @Override
    public Optional<Courier> getByUsername(String username) {
        return courierRepository.getByUsername(username);
    }


    @Override
    public List<CourierDto> getAll() {
        return courierRepository.findAll().stream().map(CourierDto::mapper).collect(Collectors.toList());
    }
}
