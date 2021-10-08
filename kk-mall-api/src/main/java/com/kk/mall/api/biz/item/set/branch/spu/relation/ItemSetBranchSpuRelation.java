package com.kk.mall.api.biz.item.set.branch.spu.relation;


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
@TableName("t_item_set_branch_spu_relation")
@ApiModel(value = "spu和品牌关系表对象", description = "spu和品牌关系表")
public class ItemSetBranchSpuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "spu主键")
    private Long spuId;

    @ApiModelProperty(value = "品牌主键")
    private Long branchId;

    @ApiModelProperty(value = "品牌内货号")
    private String branchItemCode;


}
