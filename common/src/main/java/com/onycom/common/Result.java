package com.onycom.common;

import lombok.Getter;

@Getter
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.code = ResultCode.SUCCESS.code;
        result.message = ResultCode.SUCCESS.message;
        result.data = data;
        return result;
    }

    public static <T> Result<T>success(String message){
        Result<T> result = new Result<>();
        result.code = ResultCode.SUCCESS.code;
        result.message = message;
        return  result;
    }

    public static <T> Result<T> error(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.code = resultCode.code;
        result.message = resultCode.message;
        return result;
    }

    public static <T> Result<T> error(ResultCode resultCode, String message) {
        Result<T> result = new Result<>();
        result.code = resultCode.code;
        result.message = message;
        return result;
    }
}
