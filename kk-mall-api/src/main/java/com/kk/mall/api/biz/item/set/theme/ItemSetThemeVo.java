package com.kk.mall.api.biz.item.set.theme;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Wenk.Chan
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "套组主题返回对象")
public class ItemSetThemeVo implements Serializable {

    @ApiModelProperty(value = "自增主键")
    private Long themeId;

    @ApiModelProperty(value = "主题名")
    private String themeName;

    @ApiModelProperty(value = "主题图url")
    private String themeImageUrl;

}
