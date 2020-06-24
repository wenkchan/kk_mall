package com.kk.mall.api.biz.client.auth;

import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyCommand;
import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyRepresentation;
import com.kk.mall.common.util.RedisUtil;
import com.kk.mall.api.biz.client.auth.identify.mobile.MobileQuery;
import com.kk.mall.api.biz.client.auth.identify.mobile.SmsCodeRepresentation;
import com.kk.mall.api.biz.client.auth.identify.operation.IIdentifyOperation;
import com.kk.mall.api.biz.client.auth.identify.operation.IdentifyOperationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/9 9:46 上午
 */

@Service
public class ClientAuthService {

    @Resource
    private IdentifyOperationContext identifyOperationContext;

    @Resource
    private RedisUtil redisUtil;

    public ClientIdentifyRepresentation identify(ClientIdentifyCommand clientIdentifyCommand) {
        IIdentifyOperation identifyTypeOperation = identifyOperationContext.getInstance(clientIdentifyCommand.getIdentifyType());
        return identifyTypeOperation.login(clientIdentifyCommand);
    }

    public SmsCodeRepresentation getSmsVerificationCode(MobileQuery mobileQuery) {
        String smsVerificationCode = mobileQuery.getVerificationCode();
        redisUtil.set(mobileQuery.getMobile(), smsVerificationCode, 300);
        return new SmsCodeRepresentation(smsVerificationCode);
    }

}

