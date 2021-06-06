package com.kk.mall.api.biz.item.specs.key;


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
@TableName("t_item_specs_key")
@ApiModel(value = "规格参数key表对象", description = "规格参数key表")
public class ItemSpecsKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "1 分类属性  2 搜索属性")
    private String specsType;

    @ApiModelProperty(value = "属性名")
    private String specsKey;

    @ApiModelProperty(value = "属性图标url")
    private String specsIconUrl;

    @ApiModelProperty(value = "是否数值型")
    private Boolean numeric;

    @ApiModelProperty(value = "数值型搜索范围")
    private String numericSearchSegment;

    @ApiModelProperty(value = "倒排序")
    private String sort;

    @ApiModelProperty(value = "1 有效 0 失效 -1 删除")
    private Integer valid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
