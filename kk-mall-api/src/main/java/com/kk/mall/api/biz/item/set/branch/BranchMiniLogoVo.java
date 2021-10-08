package com.kk.mall.api.biz.item.set.branch;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "品牌表mini logo对象")
public class BranchMiniLogoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long branchId;

    @ApiModelProperty(value = "品牌名")
    private String branchName;

    @ApiModelProperty(value = "品牌logo mini 尺寸")
    private String branchLogoMiniSizeUrl;

    @ApiModelProperty("品牌货号")
    private String branchItemCode;



}
