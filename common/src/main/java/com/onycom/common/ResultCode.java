package com.onycom.common;

public enum ResultCode {
    SUCCESS(200, "success"),
    BAD_REQUEST(400, "请求参数错误"),
    INTERNAL_ERROR(500, "服务器内部错误"),
    NOT_FOUND(404, "资源不存在");

    public final int code;
    public final  String message;
    ResultCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
