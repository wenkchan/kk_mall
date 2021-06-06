package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.branch.BranchMiniLogoRepresentation;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 * <p>
 * 产品列表展示类
 */
@Data
public class ItemListRepresentation {
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
    private List<BranchMiniLogoRepresentation> branchList;


    public static List<ItemListRepresentation> ofList(List<ItemListVo> itemListVos) {
        return itemListVos.stream().map(itemListVo -> {
            ItemListRepresentation itemListRepresentation = new ItemListRepresentation();
            itemListRepresentation.itemProductId = itemListVo.getItemProductId();
            itemListRepresentation.mainImageUrl = itemListVo.getMainImageUrl();
            itemListRepresentation.itemProductName = itemListVo.getItemProductName();
            itemListRepresentation.branchList = itemListVo.getBranchVoList().stream()
                    .map(branchVo -> new BranchMiniLogoRepresentation(branchVo.getProductCode(), branchVo.getBranchName(), branchVo.getLogoMiniSizeUrl())).collect(Collectors.toList());
            return itemListRepresentation;
        }).collect(Collectors.toList());
    }
}
