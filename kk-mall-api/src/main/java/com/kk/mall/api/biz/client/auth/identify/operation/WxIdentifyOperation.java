package com.kk.mall.api.biz.client.auth.identify.operation;

import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyCommand;
import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyRepresentation;
import com.kk.mall.api.biz.client.auth.identify.IdentifyType;
import org.springframework.stereotype.Component;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/12 9:02 上午
 */
@Component
@IdentifyType("WX")
public class WxIdentifyOperation implements IIdentifyOperation {

    @Override
    public ClientIdentifyRepresentation login(ClientIdentifyCommand clientIdentifyCommand) {
        return null;
    }
}

