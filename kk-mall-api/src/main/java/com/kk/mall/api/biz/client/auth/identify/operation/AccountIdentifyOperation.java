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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/12 9:02 上午
 */
@Component
@IdentifyType(IdentifyTypeConst.ACCOUNT)
public class AccountIdentifyOperation implements IIdentifyOperation {

    @Resource
    private ClientAuthAccountService clientAuthAccountService;

    @Resource
    private ClientBasicInfoMapper clientBasicInfoMapper;


    @Override
    public ClientIdentifyRepresentation login(ClientIdentifyCommand clientIdentifyCommand) {
        LambdaQueryWrapper<ClientAuthAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ClientAuthAccount::getToken, ClientAuthAccount::getRefreshToken,ClientAuthAccount::getCredential);
        queryWrapper.eq(ClientAuthAccount::getAccount, clientIdentifyCommand.getIdentifier());

        ClientAuthAccount clientAuthAccount = clientAuthAccountService.getOne(queryWrapper);

        clientAuthAccount = Optional.ofNullable(clientAuthAccount).orElseGet(() -> {
            ClientBasicInfo newClientBasicInfo = new ClientBasicInfo();
            newClientBasicInfo.createNewClient();
            clientBasicInfoMapper.insert(newClientBasicInfo);

            ClientAuthAccount newClientAuthAccount = new ClientAuthAccount();
            newClientAuthAccount.createNewClientWithAccount(newClientBasicInfo.getId(),clientIdentifyCommand.getIdentifier(),
                    clientIdentifyCommand.getCredential());
            clientAuthAccountService.save(newClientAuthAccount);
            return newClientAuthAccount;
        });

        if (!clientAuthAccount.getCredential().equals(clientIdentifyCommand.getCredential())) {
            throw new BizException(ClientAuthErrno.AUTH_FAIL);
        }

        return new ClientIdentifyRepresentation(clientAuthAccount.getToken(), clientAuthAccount.getRefreshToken());

    }

}

