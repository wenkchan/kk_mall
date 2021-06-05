package com.kk.mall.api.biz.sys.dict.item;


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
@TableName("t_sys_dict_item")
@ApiModel(value = "字典项表对象", description = "字典项表")
public class SysDictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典id")
    private String sysDictId;

    @ApiModelProperty(value = "字典项key")
    private String dictKey;

    @ApiModelProperty(value = "字典项值")
    private String dictValue;

    @ApiModelProperty(value = "描述")
    private String dictDesc;

    @ApiModelProperty(value = "排序")
    private String sort;

    @ApiModelProperty(value = "1 有效 0 无效")
    private Integer valid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
