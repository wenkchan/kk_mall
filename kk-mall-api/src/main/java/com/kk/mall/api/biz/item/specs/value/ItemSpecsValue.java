package com.kk.mall.api.biz.item.specs.value;


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
 * @author chenwenkun
 */

@Getter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_item_specs_value")
@ApiModel(value = "规格参数value表对象", description = "规格参数value表")
public class ItemSpecsValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "属性值id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "对应属性key 的id")
    private Long itemSpecsKeyId;

    @ApiModelProperty(value = "属性值")
    private String specsValue;

    @ApiModelProperty(value = "产品Id")
    private String itemProductId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
