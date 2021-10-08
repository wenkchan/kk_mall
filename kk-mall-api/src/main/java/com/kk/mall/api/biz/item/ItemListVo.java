package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.set.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.set.theme.ItemSetThemeVo;
import com.kk.mall.api.biz.item.spu.ItemSpu;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 * <p>
 * 产品列表值对象
 */
@Data
public class ItemListVo {
    private long itemId;
    /**
     * 主图url
     */
    private String itemMainPicUrl;
    /**
     * 产品名
     */
    private String itemName;
    /**
     * 品牌列表
     */
    private List<BranchMiniLogoVo> itemBranchList;


    /**
     * spu主题
     */
    private ItemSetThemeVo itemTheme;


    public static List<ItemListVo> ofList(List<ItemSpu> itemSpuList, Map<Long, List<BranchMiniLogoVo>> spuBranchMap, Map<Long, ItemSetThemeVo> themeMap) {

        return itemSpuList.stream().map(itemSpu -> {
            ItemListVo itemListVo = new ItemListVo();
            List<BranchMiniLogoVo> branchSmallLogoRespList = spuBranchMap.get(itemSpu.getId());
            itemListVo.setItemMainPicUrl(itemSpu.getSpuMainPicUrl());
            itemListVo.setItemId(itemSpu.getId());
            itemListVo.setItemName(itemSpu.getSpuName());
            itemListVo.setItemBranchList(branchSmallLogoRespList);
            itemListVo.setItemTheme(themeMap.get(itemSpu.getId()));
            return itemListVo;
        }).collect(Collectors.toList());
    }


}
