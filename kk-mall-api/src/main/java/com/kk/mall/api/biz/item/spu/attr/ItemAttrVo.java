package com.kk.mall.api.biz.item.spu.attr;

import com.kk.mall.api.biz.item.spu.attr.key.ItemSpuAttrKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 属性Vo
 */
@Data
public class ItemAttrVo {
    @ApiModelProperty("属性key")
    private String itemAttrKey;
    @ApiModelProperty("属性值")
    private String itemAttrValue;
    @ApiModelProperty("属性icon")
    private String itemAttrIcon;

    public static List<ItemAttrVo> ofList(List<ItemSpuAttrKey> itemAttrKeys,
                                                     Map<Long, String> itemAttrKeyMap) {
        return itemAttrKeys.stream().map(itemAttrKey -> {
            ItemAttrVo itemAttrVo=new ItemAttrVo();
            itemAttrVo.setItemAttrKey(itemAttrKey.getAttrKey());
            itemAttrVo.setItemAttrIcon(itemAttrKey.getAttrIconUrl());
            itemAttrVo.setItemAttrValue(itemAttrKeyMap.get(itemAttrKey.getId()));
            return itemAttrVo;
        }).collect(Collectors.toList());
    }
}

