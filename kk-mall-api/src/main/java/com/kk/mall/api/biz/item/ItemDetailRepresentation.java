package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.branch.BranchMiniLogoRepresentation;
import com.kk.mall.api.biz.item.specs.SpecsRepresentation;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 * <p>
 * 产品列表展示类
 */
@Data
public class ItemDetailRepresentation {
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
    private List<SpecsRepresentation> specsList;
    /**
     * 品牌列表
     */
    private List<BranchMiniLogoRepresentation> branchList;

    /**
     * 同系列产品
     */
    private List<ItemListRepresentation> seriesItemList;

    public static ItemDetailRepresentation of(ItemDetailVo itemDetailVo) {
        ItemDetailRepresentation itemDetailRepresentation = new ItemDetailRepresentation();
        BeanUtils.copyProperties(itemDetailVo, itemDetailRepresentation);

        itemDetailRepresentation.branchList = itemDetailVo.getBranchVoList().stream()
                .map(branchVo -> new BranchMiniLogoRepresentation(branchVo.getProductCode(), branchVo.getBranchName(), branchVo.getLogoMiniSizeUrl())).collect(Collectors.toList());
        itemDetailRepresentation.specsList = itemDetailVo.getSpecsList().stream()
                .map(specsVo -> new SpecsRepresentation(specsVo.getSpecsKey(), specsVo.getSpecsValue())).collect(Collectors.toList());

        itemDetailRepresentation.seriesItemList = ItemListRepresentation.ofList(itemDetailVo.getSeriesItemList());

        return itemDetailRepresentation;
    }
}
