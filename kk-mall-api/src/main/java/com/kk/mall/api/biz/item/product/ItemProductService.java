package com.kk.mall.api.biz.item.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.item.ItemDetailVo;
import com.kk.mall.api.biz.item.ItemErrno;
import com.kk.mall.api.biz.item.ItemListVo;
import com.kk.mall.api.biz.item.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.branch.ItemBranchService;
import com.kk.mall.api.biz.item.series.ItemSeries;
import com.kk.mall.api.biz.item.series.ItemSeriesService;
import com.kk.mall.api.biz.item.specs.SpecsVo;
import com.kk.mall.api.biz.item.specs.value.ItemSpecsValueService;
import com.kk.mall.api.enums.ValidEnum;
import com.kk.mall.api.query.PageQuery;
import com.kk.mall.common.exception.BizException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 */
@Service
public class ItemProductService extends ServiceImpl<ItemProductMapper, ItemProduct> implements IService<ItemProduct> {

    @Resource
    private ItemSpecsValueService itemSpecsValueService;

    @Resource
    private ItemBranchService itemBranchService;

    @Resource
    private ItemSeriesService itemSeriesService;

    public List<ItemListVo> geItemHomeList(PageQuery pageQuery) {
        LambdaQueryWrapper<ItemProduct> itemProductLambdaQueryWrapper = new LambdaQueryWrapper<>();
        itemProductLambdaQueryWrapper.select(
                ItemProduct::getId, ItemProduct::getProductName,
                ItemProduct::getProductEnName, ItemProduct::getMainImageUrl);
        itemProductLambdaQueryWrapper.eq(ItemProduct::getValid, ValidEnum.VALID.getValue());
        itemProductLambdaQueryWrapper.orderByDesc(ItemProduct::getCreateTime);
        Page<ItemProduct> page = page(new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize()), itemProductLambdaQueryWrapper);

        List<ItemProduct> itemProductList = page.getRecords();
        List<Long> itemProductIdList = itemProductList.stream().map(ItemProduct::getId).collect(Collectors.toList());

        List<BranchMiniLogoVo> branchMiniLogoVoList = itemBranchService.getBranchList(itemProductIdList);


        return ItemListVo.ofList(itemProductList , branchMiniLogoVoList);
    }

    public ItemDetailVo getItemDetail(Long itemProductId) {
        ItemProduct itemProduct = baseMapper.selectById(itemProductId);
        if (null == itemProduct) {
            throw new BizException(ItemErrno.PRODUCT_NOT_EXIST);
        }

        List<SpecsVo> specsVoList = itemSpecsValueService.getSpecsList(Collections.singletonList(itemProductId));
        List<BranchMiniLogoVo> currentItemBranchMiniLogoVoList = itemBranchService.getBranchList(Collections.singletonList(itemProductId));

        ItemSeries itemSeries = itemSeriesService.getById(itemProduct.getSeriesId());

        LambdaQueryWrapper<ItemProduct> itemProductLambdaQueryWrapper = new LambdaQueryWrapper<>();
        itemProductLambdaQueryWrapper.eq(ItemProduct::getSeriesId, itemSeries.getId());
        itemProductLambdaQueryWrapper.ne(ItemProduct::getId, itemProductId);
        List<ItemProduct> seriesItemProducts = list(itemProductLambdaQueryWrapper);
        List<BranchMiniLogoVo> seriesItemBranchMiniLogoVoList=new ArrayList<>();
        if (!seriesItemProducts.isEmpty()){
            List<Long> seriesItemIdList = seriesItemProducts.stream().map(ItemProduct::getId).collect(Collectors.toList());
            seriesItemBranchMiniLogoVoList = itemBranchService.getBranchList(seriesItemIdList);
        }

        return ItemDetailVo.of(itemProduct, specsVoList, currentItemBranchMiniLogoVoList,
                seriesItemProducts,seriesItemBranchMiniLogoVoList);
    }
}
