package com.kk.mall.api.biz.client.auth.account;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;


@Service
public class ClientAuthAccountService extends ServiceImpl<ClientAuthAccountMapper, ClientAuthAccount> implements IService<ClientAuthAccount> {
}
