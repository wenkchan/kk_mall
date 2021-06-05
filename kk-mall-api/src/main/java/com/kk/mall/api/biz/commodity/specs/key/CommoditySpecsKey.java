package com.kk.mall.api.biz.commodity.specs.key;


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
@TableName("t_commodity_specs_key")
@ApiModel(value = "规格参数key表对象", description = "规格参数key表")
public class CommoditySpecsKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long backendCategoryId;

    private String name;

    @ApiModelProperty(value = "是否数值型")
    private Boolean numeric;

    @ApiModelProperty(value = "数值型参数单位")
    private String unit;

    @ApiModelProperty(value = "是否通用参数")
    private Integer generic;

    @ApiModelProperty(value = "是否可用于搜索过滤")
    private Boolean searchable;

    @ApiModelProperty(value = "数值型搜索范围")
    private String numericSearchSegment;

    @ApiModelProperty(value = "倒排序")
    private String sort;

    private Integer valid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
