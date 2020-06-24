package com.kk.mall.api.biz.client.auth;

import com.kk.mall.common.exception.Errno;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/12 9:38 上午
 */
public enum ClientAuthErrno implements Errno {
    IDENTIFY_TYPE_NOT_EXIST(1001, "验证方式不存在"),
    PHONE_FORMAT_ERROR(1002, "登录手机号错误,请检查"),
    AUTH_FAIL(1003, "登录账户或密码错误,请检查"),
    SMS_CODE_FAIL(1004, "手机验证码错误,请检查");

    private Integer status;
    private String message;


    ClientAuthErrno(int status, String message) {
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

