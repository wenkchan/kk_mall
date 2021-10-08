package com.kk.mall.api.biz.item.set.series;


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
@TableName("t_item_set_series")
@ApiModel(value = "套组系列表对象", description = "套组系列表")
public class ItemSetSeries implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "系列名")
    private String name;

    @ApiModelProperty(value = "系列英文名")
    private String enName;

    @ApiModelProperty(value = "系列图url")
    private String imageUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @ApiModelProperty(value = "可用性 1 可用 0 不可用")
    private Integer valid;

    @ApiModelProperty(value = "系列描述")
    private String description;


}
