package com.kk.mall.api.biz.commodity;

import lombok.Getter;

import java.util.List;

/**
 * @author chenwenkun
 * <p>
 * 商品列表展示类
 */
@Getter
public class CommodityListRepresentation {
    private long skuId;
    /**
     * 主图url
     */
    private String mainImageUrl;
    /**
     * spu标题
     */
    private String spuTitle;
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
