package com.kk.mall.api.biz.client.auth.identify;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/5/27 4:35 下午
 */
@Data
public class ClientIdentifyRepresentation {
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("refreshToken")
    private String refreshToken;

    public ClientIdentifyRepresentation(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}

