package com.kk.mall.api.biz.client.auth.identify.operation;

import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyCommand;
import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyRepresentation;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/12 9:23 上午
 */

public interface IIdentifyOperation {
    ClientIdentifyRepresentation login(ClientIdentifyCommand clientIdentifyCommand);
}

