package com.kk.mall.api.biz.item.tag;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author chenwenkun
 */

@Getter
public class ItemTagVo  {

    @ApiModelProperty(value = "标签名")
    private String tagName;

    @ApiModelProperty(value = "倒排序")
    private Integer sort;

}
