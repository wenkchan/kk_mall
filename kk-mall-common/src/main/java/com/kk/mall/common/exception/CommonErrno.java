package com.kk.mall.common.exception;


public enum CommonErrno implements Errno {
    SUCCESS(200, "成功"),
    REQUEST_PARAMS_VALIDATION_FAILED(400, "请求参数有误"),
    NOT_FOUND(404, "请求路径未找到"),
    UNAUTHORIZED(401, "token无效"),
    SYSTEM_ERROR(500, "服务器内部错误");
    private Integer status;
    private String message;


    CommonErrno(int status, String message) {
        this.message = message;
        this.status = status;
    }


    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
