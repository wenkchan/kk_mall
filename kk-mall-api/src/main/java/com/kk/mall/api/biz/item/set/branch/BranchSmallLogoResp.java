package com.kk.mall.api.biz.item.set.branch;


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
@ApiModel(value = "品牌表返回对象")
public class BranchSmallLogoResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    private Long id;

    @ApiModelProperty(value = "品牌名")
    private String branchName;


    @ApiModelProperty(value = "品牌logo 小图 尺寸")
    private String branchLogoSmallSizeUrl;



}
