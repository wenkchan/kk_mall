package com.kk.mall.api.biz.client.basic.info;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kk_client_basic_info")
@ApiModel(value = "ClientBasicInfo对象", description = "用户基础信息表")
public class ClientBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "性别 m 男 f 女 u 未指定")
    private String gender;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    private Boolean isActive;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    public void createNewClient() {
        this.isActive = true;
        this.gender = Gender.U.toString();
    }
}
