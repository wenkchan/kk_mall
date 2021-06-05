package com.kk.mall.api.biz.category.backend;


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
@TableName("t_category_backend")
@ApiModel(value = "后端分类表对象", description = "后端分类表")
public class CategoryBackend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "后端分类名称")
    private String name;

    @ApiModelProperty(value = "上级分类id ,0为一级分类")
    private Long parentId;

    @ApiModelProperty(value = "分类描述")
    private String description;


    @ApiModelProperty(value = "1 有效 0 无效")
    private Integer valid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
