package com.mscourier.dto.request;

import com.mscourier.constraints.CourierVehicleSubSet;
import com.mscourier.enums.CourierVehicle;

import java.io.Serializable;

import static com.mscourier.enums.CourierVehicle.*;

public class CreateCourierRequest implements Serializable {

    private String username;
    private String password;
    private String name;
    private String surname;
    @CourierVehicleSubSet(anyOf ={BICYCLE, SCOOTER, CAR, MOPED, MOTORBIKE})
    private CourierVehicle courierVehicle;

    public String getUsername() {
        return username;
    }

    public CreateCourierRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateCourierRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateCourierRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CreateCourierRequest setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public CourierVehicle getCourierVehicle() {
        return courierVehicle;
    }

    public CreateCourierRequest setCourierVehicle(CourierVehicle courierVehicle) {
        this.courierVehicle = courierVehicle;
        return this;
    }
}
