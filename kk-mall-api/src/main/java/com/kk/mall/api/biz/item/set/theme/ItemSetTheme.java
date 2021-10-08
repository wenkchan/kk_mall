package com.kk.mall.api.biz.item.set.theme;


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
@TableName("t_item_set_theme")
@ApiModel(value = "套组主题表对象", description = "套组主题表")
public class ItemSetTheme implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主题名")
    private String themeName;

    @ApiModelProperty(value = "主题图url")
    private String themeImageUrl;

    @ApiModelProperty(value = "主题描述")
    private String themeDescription;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "可用性 1可用 0 不可用")
    private Boolean valid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
