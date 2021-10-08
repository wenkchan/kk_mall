package com.kk.mall.api.biz.item.spu;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Wenk.Chan
 */

@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_item_spu")
@ApiModel(value = "spu表对象", description = "spu表")
public class ItemSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "spu名")
    private String spuName;

    @ApiModelProperty(value = "spu英文名")
    private String spuEnName;

    @ApiModelProperty(value = "可用性1 可用 0不可用")
    private Integer valid;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    @ApiModelProperty(value = "spu描述")
    private String description;

    @ApiModelProperty("主图")
    private String spuMainPicUrl;



}
