package com.kk.mall.api.biz.item.set.series;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.item.ItemFacadeService;
import com.kk.mall.api.biz.item.ItemListVo;
import com.kk.mall.api.biz.item.set.series.spu.relation.ItemSetSeriesSpuRelation;
import com.kk.mall.api.biz.item.set.series.spu.relation.ItemSetSeriesSpuRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Wenk.Chan
 * 2021年10月03日10:56:30
 */
@Service
public class ItemSetSeriesService extends ServiceImpl<ItemSetSeriesMapper, ItemSetSeries> implements IService<ItemSetSeries> {


    @Resource
    public ItemSetSeriesSpuRelationService itemSetSeriesSpuRelationService;

    @Resource
    public ItemFacadeService itemFacadeService;

    /**
     * 获取同系列的其他spu
     *
     * @param spuId
     * @return
     */
    public List<ItemListVo> getSameSeriesBySpuId(Long spuId) {
        ItemSetSeriesSpuRelation setSeriesSpuRelation = itemSetSeriesSpuRelationService
                .getOne(Wrappers.<ItemSetSeriesSpuRelation>lambdaQuery()
                        .eq(ItemSetSeriesSpuRelation::getSpuId, spuId));

        if (null == setSeriesSpuRelation) {
            return new ArrayList<>();
        }
        List<ItemSetSeriesSpuRelation> seriesSpuRelations = itemSetSeriesSpuRelationService
                .list(Wrappers.<ItemSetSeriesSpuRelation>lambdaQuery()
                        .eq(ItemSetSeriesSpuRelation::getSeriesId, setSeriesSpuRelation.getSeriesId()));


        List<Long> spuIdList = seriesSpuRelations.stream()
                .map(ItemSetSeriesSpuRelation::getSpuId).collect(Collectors.toList());

        //过滤掉自身
        spuIdList = spuIdList.stream().filter(tempSpuId -> !tempSpuId.equals(spuId)).collect(Collectors.toList());

        return itemFacadeService.getItemInfoBySpuIds(spuIdList);
    }

}
