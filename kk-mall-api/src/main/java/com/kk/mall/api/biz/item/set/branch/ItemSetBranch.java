package com.kk.mall.api.biz.item.set.branch;


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
@TableName("t_item_set_branch")
@ApiModel(value = "品牌表对象", description = "品牌表")
public class ItemSetBranch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "品牌名")
    private String branchName;

    @ApiModelProperty(value = "品牌描述")
    private String branchDescription;

    @ApiModelProperty(value = "品牌logo mini 尺寸")
    private String branchLogoMiniSizeUrl;

    @ApiModelProperty(value = "品牌logo 小图 尺寸")
    private String branchLogoSmallSizeUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "可用性 1 可用 0 不可用")
    private Boolean valid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
