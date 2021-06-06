package com.kk.mall.api.biz.item.product;


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
@TableName("t_item_product")
@ApiModel(value = "产品表对象", description = "产品表")
public class ItemProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品名")
    private String productName;

    @ApiModelProperty(value = "产品英文名")
    private String productEnName;

    @ApiModelProperty(value = "主图")
    private String mainImageUrl;

    @ApiModelProperty(value = "品牌系列id")
    private Long seriesId;

    @ApiModelProperty(value = "介绍图")
    private Integer descriptionImageUrls;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "1 有效 0 无效 -1 删除")
    private Integer valid;


}
