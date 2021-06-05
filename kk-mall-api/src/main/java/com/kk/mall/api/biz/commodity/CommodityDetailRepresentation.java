package com.kk.mall.api.biz.commodity;

import lombok.Getter;

import java.util.List;

/**
 * @author chenwenkun
 * <p>
 * 商品详情展示类
 */
@Getter
public class CommodityDetailRepresentation {
    private long skuId;
    /**
     * 主图url
     */
    private String mainImageUrl;
    /**
     * spu标题
     */
    private String spuTitle;
    /***
     * spu英文标题
     */
    private String spuEnglishTitle;
    /**
     * 品牌列表
     */
    private List<Branch> branchList;

    static class Branch {
        /**
         * 品牌logo
         */
        private String branchMiniLogoUrl;
        /**
         * 品牌名
         */
        private String branchName;
        /**
         * 编号
         */
        private String itemNumber;
    }

    /**
     * 发行年份
     */
    private String releaseYear;
    /**
     * 系列名
     */
    private String seriesName;
}
