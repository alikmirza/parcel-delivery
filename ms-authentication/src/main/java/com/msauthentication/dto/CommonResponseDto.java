package com.msauthentication.dto;


import java.io.Serializable;
import java.math.BigInteger;

public class CommonResponseDto<T> implements Serializable {

    private BigInteger timestamp;
    private String status;
    private Integer code;
    private T data;


    public CommonResponseDto() {}

    public CommonResponseDto(BigInteger timestamp, String status, Integer code, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public CommonResponseDto<T> setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CommonResponseDto<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public CommonResponseDto<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResponseDto<T> setData(T data) {
        this.data = data;
        return this;
    }
}
