package com.kk.mall.api.biz.item.spu.attr.relation;


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
@TableName("t_item_spu_attr_relation")
@ApiModel(value = "属性值和spu的关系表对象", description = "属性值和spu的关系表")
public class ItemSpuAttrRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "spu主键")
    private Long spuId;

    @ApiModelProperty(value = "属性值主键")
    private Long attrValueId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
