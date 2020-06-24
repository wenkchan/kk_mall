package com.kk.mall.common.result;

import cn.hutool.core.util.IdUtil;
import com.kk.mall.common.exception.CommonErrno;
import com.kk.mall.common.exception.Errno;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResult<T> implements Serializable {

    private String requestId;
    private String path;
    private long timestamp;
    private boolean success;
    private Integer status;
    private String message;
    private T data;


    public ApiResult(Errno errno, boolean success, String path, T data) {
        this.message = errno.getMessage();
        this.status = errno.getStatus();
        this.success = success;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }


    public static <T> ApiResult<T> success(T data, String path) {
        return new ApiResult<T>(CommonErrno.SUCCESS, true, path, data);
    }

    public static <T> ApiResult<T> error(Errno errno, String path, T data, boolean needRequestId) {
        ApiResult<T> apiResult = new ApiResult<>(errno, false, path, data);
        if (needRequestId) {
            apiResult.setRequestId(IdUtil.simpleUUID());
        }
        return apiResult;
    }
}
