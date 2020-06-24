package com.kk.mall.api.biz.client.auth.identify.operation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kk.mall.api.biz.client.auth.ClientAuthErrno;
import com.kk.mall.api.biz.client.auth.account.ClientAuthAccount;
import com.kk.mall.api.biz.client.auth.account.ClientAuthAccountService;
import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyCommand;
import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyRepresentation;
import com.kk.mall.api.biz.client.auth.identify.IdentifyType;
import com.kk.mall.api.biz.client.auth.identify.IdentifyTypeConst;
import com.kk.mall.api.biz.client.basic.info.ClientBasicInfo;
import com.kk.mall.api.biz.client.basic.info.ClientBasicInfoMapper;
import com.kk.mall.common.exception.BizException;
import com.kk.mall.common.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/28 4:46 下午
 */
@Component
@IdentifyType(IdentifyTypeConst.SMS)
public class SmsCodeIdentifyOperation implements IIdentifyOperation {


    @Resource
    private ClientAuthAccountService clientAuthAccountService;

    @Resource
    private ClientBasicInfoMapper clientBasicInfoMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public ClientIdentifyRepresentation login(ClientIdentifyCommand clientIdentifyCommand) {
        clientIdentifyCommand.isPhone();

        String smsCode = (String) redisUtil.get(clientIdentifyCommand.getIdentifier());
        if (!clientIdentifyCommand.getCredential().equals(smsCode)) {
            throw new BizException(ClientAuthErrno.SMS_CODE_FAIL);
        }

        LambdaQueryWrapper<ClientAuthAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ClientAuthAccount::getToken, ClientAuthAccount::getRefreshToken, ClientAuthAccount::getCredential);
        queryWrapper.eq(ClientAuthAccount::getAccount, clientIdentifyCommand.getIdentifier());

        ClientAuthAccount clientAuthAccount = clientAuthAccountService.getOne(queryWrapper);

        clientAuthAccount = Optional.ofNullable(clientAuthAccount).orElseGet(() -> {
            ClientBasicInfo newClientBasicInfo = new ClientBasicInfo();
            newClientBasicInfo.createNewClient();
            clientBasicInfoMapper.insert(newClientBasicInfo);

            ClientAuthAccount newClientAuthAccount = new ClientAuthAccount();
            newClientAuthAccount.createNewClientWithMobile(newClientBasicInfo.getId(), clientIdentifyCommand.getIdentifier());
            clientAuthAccountService.save(newClientAuthAccount);
            return newClientAuthAccount;
        });

        redisUtil.del(clientIdentifyCommand.getIdentifier());

        return new ClientIdentifyRepresentation(clientAuthAccount.getToken(), clientAuthAccount.getRefreshToken());
    }
}

