package com.msdelivery.exception;

import com.msdelivery.entity.Order;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

public class CommonAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public CommonAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public static void throwOnStatus(Order order, String status) {
        Predicate<String> predicate = p -> p.equals(status);
        if (predicate.test(order.getStatus().name())) throw new CommonAPIException(HttpStatus.BAD_REQUEST, String.format("Order is already %s", status));
    }
    public static void throwOnStatusNegation(Order order, String status) {
        Predicate<String> predicate = p -> !p.equals(status);
        if (predicate.test(order.getStatus().name())) throw new CommonAPIException(HttpStatus.BAD_REQUEST, String.format("Order is already %s", status));
    }

    public static <T> T throwOnNotFound(Optional<T> order, Long id) {
        return order.orElseThrow(() -> new CommonAPIException(HttpStatus.BAD_REQUEST, String.format("Order with %d id does not exists", id)));
    }

}
