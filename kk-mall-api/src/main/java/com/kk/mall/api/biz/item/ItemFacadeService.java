package com.kk.mall.api.biz.item;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.mall.api.biz.item.set.branch.BranchMiniLogoVo;
import com.kk.mall.api.biz.item.set.branch.ItemSetBranchService;
import com.kk.mall.api.biz.item.set.series.ItemSetSeriesService;
import com.kk.mall.api.biz.item.set.theme.ItemSetThemeVo;
import com.kk.mall.api.biz.item.set.theme.ItemSetThemeService;
import com.kk.mall.api.biz.item.spu.ItemSpu;
import com.kk.mall.api.biz.item.spu.ItemSpuService;
import com.kk.mall.api.biz.item.spu.attr.ItemAttrFacadeService;
import com.kk.mall.api.biz.item.spu.attr.ItemAttrVo;
import com.kk.mall.api.enums.ValidEnum;
import com.kk.mall.api.query.PageQuery;
import com.kk.mall.common.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 */
@Service
public class ItemFacadeService {

    @Resource
    private ItemSpuService itemSpuService;

    @Resource
    private ItemSetBranchService itemSetBranchService;

    @Resource
    private ItemSetSeriesService itemSetSeriesService;

    @Resource
    private ItemAttrFacadeService itemAttrFacadeService;

    @Resource
    private ItemSetThemeService itemSetThemeService;

    public Page<ItemListResp> getItemHomeList(PageQuery pageQuery) {
        //查询spu信息
        Page<ItemSpu> itemSpuPage = itemSpuService
                .page(new Page<>(pageQuery.getPage(), pageQuery.getSize()),
                        Wrappers.<ItemSpu>lambdaQuery()
                                .eq(ItemSpu::getValid, ValidEnum.VALID.getValue()));
        List<ItemSpu> itemSpuList = itemSpuPage.getRecords();

        if (CollectionUtils.isEmpty(itemSpuList)) {
            return new Page<>();
        }

        Page<ItemListResp> itemSpuPageResult = new Page<>();
        BeanUtils.copyProperties(itemSpuPage, itemSpuPageResult);
        List<ItemListVo> itemListVoList = getItemInfoBySpuIds(itemSpuList.stream().map(ItemSpu::getId).collect(Collectors.toList()));

        itemSpuPageResult.setRecords(ItemListResp.ofList(itemListVoList));
        return itemSpuPageResult;
    }


    public ItemDetailResp getItemDetail(Long spuId) {
        //查询spu信息
        ItemSpu itemSpu = itemSpuService.getById(spuId);

        if (null == itemSpu) {
            throw new BizException(ItemErrno.PRODUCT_NOT_EXIST);
        }

        //查询关联品牌信息
        Map<Long, List<BranchMiniLogoVo>> spuBranchMap =
                itemSetBranchService.getItemSetBranchListBySpuIds(Collections.singletonList(spuId));
        List<BranchMiniLogoVo> branchList = spuBranchMap.get(spuId);

        List<ItemAttrVo> itemAttrVos = itemAttrFacadeService.getItemAttrBySpuIds(Collections.singletonList(spuId));

        //查询同系列相关信息
        List<ItemListVo> sameSeriesItem =
                itemSetSeriesService.getSameSeriesBySpuId(spuId);

        Map<Long, ItemSetThemeVo> themeMap = itemSetThemeService.getThemeBySpuIds(Collections.singletonList(spuId));


        return ItemDetailResp.of(itemAttrVos,itemSpu, branchList,sameSeriesItem,themeMap);
    }

    public List<ItemListVo> getItemInfoBySpuIds(Collection<Long> spuIds) {

        if (CollectionUtils.isEmpty(spuIds)){
            return new ArrayList<>();
        }

        List<ItemSpu> itemSpuList = itemSpuService.list(Wrappers.<ItemSpu>lambdaQuery()
                .eq(ItemSpu::getValid, ValidEnum.VALID.getValue())
                .in(ItemSpu::getId, spuIds));


        List<Long> spuIdList = itemSpuList.stream().map(ItemSpu::getId).collect(Collectors.toList());
        //查询关联品牌信息
        Map<Long, List<BranchMiniLogoVo>> branchMap =
                itemSetBranchService.getItemSetBranchListBySpuIds(spuIdList);


        Map<Long, ItemSetThemeVo> themeMap = itemSetThemeService.getThemeBySpuIds(spuIds);

        return ItemListVo.ofList(itemSpuList, branchMap,themeMap);
    }
}
