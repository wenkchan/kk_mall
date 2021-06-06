package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.product.ItemProduct;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 * <p>
 * 产品列表值对象
 */
@Getter
public class ItemListVo {
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
     * 品牌列表
     */
    private List<BranchMiniLogoVo> branchVoList;


    public static List<ItemListVo> ofList(List<ItemProduct> itemProductList, List<BranchMiniLogoVo> branchMiniLogoVoList) {
        return itemProductList.stream().map(itemProduct -> {
            ItemListVo itemListVo = new ItemListVo();
            itemListVo.itemProductId = itemProduct.getId();
            itemListVo.mainImageUrl = itemProduct.getMainImageUrl();
            itemListVo.itemProductName = itemProduct.getProductName();
            itemListVo.branchVoList = branchMiniLogoVoList.stream()
                    .filter(branchVo -> branchVo.getItemProductId().equals(itemProduct.getId())).collect(Collectors.toList());
            return itemListVo;
        }).collect(Collectors.toList());
    }

    public static ItemListVo of(ItemProduct itemProduct, List<BranchMiniLogoVo> branchMiniLogoVoList) {
        ItemListVo itemListVo = new ItemListVo();
        itemListVo.itemProductId = itemProduct.getId();
        itemListVo.mainImageUrl = itemProduct.getMainImageUrl();
        itemListVo.itemProductName = itemProduct.getProductName();
        itemListVo.branchVoList = branchMiniLogoVoList.stream()
                .filter(branchVo -> branchVo.getItemProductId().equals(itemProduct.getId())).collect(Collectors.toList());
        return itemListVo;
    }


}
