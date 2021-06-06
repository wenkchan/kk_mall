package com.kk.mall.api.biz.item.product.branch.relation;


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
@TableName("t_item_product_branch_relation")
@ApiModel(value = "对象", description = "")
public class ItemProductBranchRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品id")
    private Long itemProductId;

    @ApiModelProperty(value = "品牌id")
    private Long itemBranchId;

    @ApiModelProperty(value = "产品编号")
    private String productCode;

    @ApiModelProperty(value = "1 有效 0 无效 -1 删除")
    private Integer valid;


}
