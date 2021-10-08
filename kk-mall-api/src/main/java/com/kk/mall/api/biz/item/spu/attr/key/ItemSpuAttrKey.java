package com.kk.mall.api.biz.item.spu.attr.key;


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
@TableName("t_item_spu_attr_key")
@ApiModel(value = "属性key表对象", description = "属性key表")
public class ItemSpuAttrKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "属性key名")
    private String attrKey;

    @ApiModelProperty(value = "属性key图标url")
    private String attrIconUrl;

    @ApiModelProperty(value = "属性值是否为数字 1 数字 2 文本")
    private Boolean numerical;

    @ApiModelProperty(value = "如果numerical是数字，数字的范围  如:1-2,2-3")
    private String numericalSearchSegment;

    @ApiModelProperty(value = "属性排序")
    private Long sort;

    @ApiModelProperty(value = "可用性 1 可用 2 不可用")
    private Integer valid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @ApiModelProperty(value = "可用于搜索过滤 1 可用2 不可用")
    private Boolean searchable;


}
