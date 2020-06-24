package com.kk.mall.api.biz.client.auth.identify.operation;

import com.kk.mall.api.biz.client.auth.ClientAuthErrno;
import com.kk.mall.common.exception.BizException;
import com.kk.mall.common.util.SpringBeanUtil;

import java.util.Map;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/14 3:37 下午
 */
public class IdentifyOperationContext {
    private Map<String, Class<?>> identifyTypeMap;

    public IdentifyOperationContext(Map<String, Class<?>> identifyTypeMap) {
        this.identifyTypeMap = identifyTypeMap;
    }

    public IIdentifyOperation getInstance(String type) {
        Class<?> clazz = identifyTypeMap.get(type);
        if (clazz == null) {
            throw new BizException(ClientAuthErrno.IDENTIFY_TYPE_NOT_EXIST);
        }
        return (IIdentifyOperation) SpringBeanUtil.getBean(clazz);
    }


}

