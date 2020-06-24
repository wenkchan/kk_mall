package com.kk.mall.api.biz.client.auth.identify.mobile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/28 5:42 下午
 */
@Data
public class SmsCodeRepresentation {
    @ApiModelProperty("短信验证码")
    private String smsCode;


    public SmsCodeRepresentation(String smsCode) {
        this.smsCode = smsCode;
    }
}

