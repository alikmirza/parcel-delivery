package com.msauthentication.exception;

import com.msauthentication.dto.request.CreateUserRequestDto;
import org.springframework.http.HttpStatus;

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


    public static void throwOnExistence(boolean condition, CreateUserRequestDto requestDto) {
        Predicate<Boolean> predicate = p -> false;
        if (predicate.test(condition))
            throw new CommonAPIException(
                    HttpStatus.BAD_REQUEST, String.format("Username %s is already taken", requestDto.getUsername())
            );
    }
}
