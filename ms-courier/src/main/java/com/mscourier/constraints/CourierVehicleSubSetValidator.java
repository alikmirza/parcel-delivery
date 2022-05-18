package com.mscourier.constraints;

import com.mscourier.enums.CourierVehicle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CourierVehicleSubSetValidator implements ConstraintValidator<CourierVehicleSubSet, CourierVehicle> {

    private CourierVehicle[] subSet;

    @Override
    public void initialize(CourierVehicleSubSet constraintAnnotation) {
        this.subSet = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(CourierVehicle value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subSet).contains(value);
    }
}
