package com.kk.mall.api.biz.item;


import com.kk.mall.common.exception.Errno;

public enum ItemErrno implements Errno {
    PRODUCT_NOT_EXIST(400, "套组不存在");
    private Integer status;
    private String message;


    ItemErrno(int status, String message) {
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
