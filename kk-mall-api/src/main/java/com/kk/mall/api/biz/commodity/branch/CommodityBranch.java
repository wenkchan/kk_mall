package com.kk.mall.api.biz.commodity.branch;


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
@TableName("t_commodity_branch")
@ApiModel(value = "品牌表对象", description = "品牌表")
public class CommodityBranch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "品牌名")
    private String name;

    @ApiModelProperty(value = "品牌描述")
    private String description;

    @ApiModelProperty(value = "logo迷你图链接")
    private String logoMiniSizeUrl;

    @ApiModelProperty(value = "logo小图链接")
    private String logoSmallSizeUrl;

    @ApiModelProperty(value = "倒排序字段")
    private Integer sort;

    @ApiModelProperty(value = "1 有效 0 失效 -1 删除")
    private Boolean valid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
