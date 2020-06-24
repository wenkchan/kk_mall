package com.kk.mall.common.exception;

import org.springframework.util.CollectionUtils;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class BizException extends RuntimeException {

    private final Errno error;
    private final Map<String, Object> data = newHashMap();



    public BizException(Errno error) {
        super(format(error.getStatus(), error.getMessage(), null));
        this.error = error;
    }

    public BizException(Errno error, Map<String, Object> data) {
        super(format(error.getStatus(), error.getMessage(), data));
        this.error = error;
        if (!CollectionUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }


    public BizException(Errno error, Map<String, Object> data, Throwable cause) {
        super(format(error.getStatus(), error.getMessage(), data), cause);
        this.error = error;
        if (!CollectionUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    private static String format(Integer status, String message, Map<String, Object> data) {
        return String.format("[%s]%s:%s.", status, message, CollectionUtils.isEmpty(data) ? "" : data.toString());
    }

    public Errno getError() {
        return error;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
