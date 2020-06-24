package com.kk.mall.api.biz.client.auth;

import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyCommand;
import com.kk.mall.api.biz.client.auth.identify.ClientIdentifyRepresentation;
import com.kk.mall.api.biz.client.auth.identify.mobile.MobileQuery;
import com.kk.mall.api.biz.client.auth.identify.mobile.SmsCodeRepresentation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@AllArgsConstructor
@RequestMapping("/client/auth")
@Api(value = "客户登录验证相关Api", tags = {"客户登录验证相关Api"})
public class ClientAuthController {

    @Resource
    private ClientAuthService clientAuthService;

    @PostMapping("identification")
    @ApiOperation("登录或注册")
    public ClientIdentifyRepresentation identify(@Validated ClientIdentifyCommand clientIdentifyCommand) {
        return clientAuthService.identify(clientIdentifyCommand);
    }

    @GetMapping("sms/verification/code")
    @ApiOperation("获取手机验证码")
    public SmsCodeRepresentation getSmsVerificationCode(@Validated MobileQuery mobileQuery) {
        return clientAuthService.getSmsVerificationCode(mobileQuery);
    }

}
