package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.set.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.set.theme.ItemSetThemeVo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 * <p>
 * 产品列表值对象
 */
@Data
public class ItemListResp {
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


    public static List<ItemListResp> ofList(List<ItemListVo> itemListVoList) {
        return itemListVoList.stream().map(itemListVo -> {
            ItemListResp itemListResp = new ItemListResp();
            itemListResp.setItemId(itemListVo.getItemId());
            itemListResp.setItemBranchList(itemListVo.getItemBranchList());
            itemListResp.setItemTheme(itemListVo.getItemTheme());
            itemListResp.setItemMainPicUrl(itemListVo.getItemMainPicUrl());
            itemListResp.setItemName(itemListVo.getItemName());
            return itemListResp;
        }).collect(Collectors.toList());
    }
}
