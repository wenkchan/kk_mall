package com.kk.mall.api.biz.client.auth.identify;

import com.kk.mall.common.exception.BizException;
import com.kk.mall.api.biz.client.auth.ClientAuthErrno;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/8 4:12 下午
 */
@Data
public class ClientIdentifyCommand {
    @ApiParam(value = "登录类型 ACCOUNT 账户 SMS 短信 ", required = true)
    @NotBlank(message = "登录类型")
    private String identifyType;

    @ApiParam(value = "登录名", required = true)
    @NotBlank(message = "登录名不能为空")
    @Min(message = "登录名不能少于8位", value = 8)
    private String identifier;

    @ApiParam(value = "登录凭证", required = true)
    @NotBlank(message = "登录凭证不能为空")
    private String credential;

    public void isPhone() {
        String regex = "^(1[3-9])\\d{9}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(this.identifier);
        if (!m.matches()) {
            throw new BizException(ClientAuthErrno.PHONE_FORMAT_ERROR);
        }
    }
}

