package com.kk.mall.common.result;

import com.kk.mall.common.Const;
import com.kk.mall.common.exception.CommonErrno;
import com.kk.mall.common.exception.Errno;
import lombok.Getter;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Getter
public class ApiResult<T> implements Serializable {

    private String requestId;
    private String path;
    private long timestamp;
    private boolean success;
    private Integer status;
    private String message;
    private T data;


    private ApiResult(Errno errno, boolean success, String path, T data, String message) {
        this.message = errno.getMessage();
        this.status = errno.getStatus();
        this.success = success;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
        this.requestId = MDC.get(Const.REQUEST_ID);

        if (!StringUtils.isEmpty(message)) {
            this.message = message;
        }
    }


    static <T> ApiResult<T> success(T data, String path) {
        return new ApiResult<T>(CommonErrno.SUCCESS, true, path, data, null);
    }

    public static <T> ApiResult<T> error(Errno errno, String path, T data, String message) {
        return new ApiResult<>(errno, false, path, data, message);
    }

    public static <T> ApiResult<T> error(Errno errno, String path, T data) {
        return new ApiResult<>(errno, false, path, data, null);
    }
}
