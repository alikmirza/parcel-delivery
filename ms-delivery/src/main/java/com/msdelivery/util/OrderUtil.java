package com.msdelivery.util;

import java.math.BigDecimal;

public class OrderUtil {
    private static final BigDecimal DELIVERY_TARIFF_VALUE = new BigDecimal("2");
    private static final BigDecimal DELIVERY_TARIFF_DISCOUNT_VALUE = new BigDecimal("1.5");

    public static BigDecimal calculateDeliveryAmount(Double weight, BigDecimal amount) {
        return amount.doubleValue() < 10 ?
                DELIVERY_TARIFF_VALUE.multiply(BigDecimal.valueOf(weight)) :
                DELIVERY_TARIFF_DISCOUNT_VALUE.multiply(BigDecimal.valueOf(weight));
    }
}
