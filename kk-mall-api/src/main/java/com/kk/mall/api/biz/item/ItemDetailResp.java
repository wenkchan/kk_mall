package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.set.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.set.theme.ItemSetThemeVo;
import com.kk.mall.api.biz.item.spu.ItemSpu;
import com.kk.mall.api.biz.item.spu.attr.ItemAttrVo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author chenwenkun
 * <p>
 * 产品列表展示类
 */
@Data
public class ItemDetailResp {
    private Long itemId;
    /**
     * 主图url
     */
    private String itemMainPicUrl;
    /**
     * 产品名
     */
    private String itemName;
    /**
     * 产品英文名
     */
    private String itemEnName;

    /**
     * spu属性列表
     */
    private List<ItemAttrVo> itemAttrList;
    /**
     * 品牌列表
     */
    private List<BranchMiniLogoVo> itemBranchList;

    /**
     * 主题
     */
    private ItemSetThemeVo itemTheme;

    /**
     * 同系列产品
     */
    private List<ItemListVo> sameSeriesItemList;


    public static ItemDetailResp of(List<ItemAttrVo> spuAttrVoList, ItemSpu itemSpu,
                                    List<BranchMiniLogoVo> branchList, List<ItemListVo> sameSeriesItem, Map<Long, ItemSetThemeVo> themeMap) {
        ItemDetailResp itemDetailResp = new ItemDetailResp();
        itemDetailResp.setItemId(itemSpu.getId());
        itemDetailResp.setItemMainPicUrl(itemSpu.getSpuMainPicUrl());
        itemDetailResp.setItemName(itemSpu.getSpuName());
        itemDetailResp.setItemEnName(itemSpu.getSpuEnName());
        itemDetailResp.setItemAttrList(spuAttrVoList);
        itemDetailResp.setItemBranchList(branchList);
        itemDetailResp.setSameSeriesItemList(sameSeriesItem);
        itemDetailResp.setItemTheme(themeMap.get(itemSpu.getId()));
        return itemDetailResp;
    }

}
