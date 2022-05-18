package com.msauthentication.constraints;

import com.msauthentication.enums.UserRoles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class UserRolesSubSetValidator implements ConstraintValidator<UserRolesSubSet, UserRoles> {

    private UserRoles[] subSet;

    @Override
    public void initialize(UserRolesSubSet constraintAnnotation) {
        this.subSet = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(UserRoles value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subSet).contains(value);
    }
}
