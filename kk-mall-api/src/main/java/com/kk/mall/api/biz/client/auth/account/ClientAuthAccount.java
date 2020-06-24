package com.kk.mall.api.biz.client.auth.account;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kk_client_auth_account")
@ApiModel(value = "ClientAuthSelf对象", description = "")
public class ClientAuthAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "客户ID")
    private Long clientId;

    @ApiModelProperty(value = "客户登录名")
    private String account;

    @ApiModelProperty(value = "客户登录凭证")
    private String credential;

    private String token;

    private String refreshToken;

    private LocalDateTime tokenExpireTime;

    private LocalDateTime refreshTokenExpireTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    public void createNewClientWithAccount(Long clientId, String account, String credential) {
        this.account = account;
        this.clientId = clientId;
        this.credential = credential;
        this.refreshToken = IdUtil.simpleUUID();
        this.token = IdUtil.simpleUUID();
    }

    public void createNewClientWithMobile(Long clientId, String account) {
        createNewClientWithAccount(clientId, account, "");
    }
}
