package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.product.ItemProduct;
import com.kk.mall.api.biz.item.specs.SpecsVo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 * <p>
 * 产品详情--基础信息展示类
 */
@Data
public class ItemDetailVo {
    private long itemProductId;
    /**
     * 主图url
     */
    private String mainImageUrl;
    /**
     * 产品名
     */
    private String itemProductName;
    /**
     * 产品英文名
     */
    private String itemProductEnName;

    /**
     * 属性列表
     */
    private List<SpecsVo> specsList;
    /**
     * 品牌列表
     */
    private List<BranchMiniLogoVo> branchVoList;

    /**
     * 同系列产品
     */
    private List<ItemListVo> seriesItemList;


    public static ItemDetailVo of(ItemProduct itemProduct, List<SpecsVo> specsVoList, List<BranchMiniLogoVo> branchMiniLogoVoList,
                                  List<ItemProduct> seriesItemProducts, List<BranchMiniLogoVo> seriesItemBranchMiniLogoVoList) {
        ItemDetailVo itemDetailVo = new ItemDetailVo();
        itemDetailVo.itemProductId = itemProduct.getId();
        itemDetailVo.mainImageUrl = itemDetailVo.getMainImageUrl();
        itemDetailVo.itemProductName = itemProduct.getProductName();
        itemDetailVo.itemProductEnName = itemProduct.getProductEnName();
        itemDetailVo.specsList = specsVoList;
        itemDetailVo.branchVoList = branchMiniLogoVoList;
        itemDetailVo.seriesItemList = seriesItemProducts.stream().map(seriesItemProduct -> {
            List<BranchMiniLogoVo> miniLogoVoList = seriesItemBranchMiniLogoVoList.stream()
                    .filter(branchMiniLogoVo -> branchMiniLogoVo.getItemProductId().equals(seriesItemProduct.getId()))
                    .collect(Collectors.toList());

            return ItemListVo.of(seriesItemProduct, miniLogoVoList);
        }).collect(Collectors.toList());
        return itemDetailVo;
    }
}
