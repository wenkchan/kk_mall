package com.kk.mall.api.biz.item.set.theme;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.item.set.theme.spu.relation.ItemSetThemeSpuRelation;
import com.kk.mall.api.biz.item.set.theme.spu.relation.ItemSetThemeSpuRelationService;
import com.kk.mall.api.enums.ValidEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wenk.Chan
 */
@Service
public class ItemSetThemeService extends ServiceImpl<ItemSetThemeMapper, ItemSetTheme> implements IService<ItemSetTheme> {

    @Resource
    private ItemSetThemeSpuRelationService itemSetThemeSpuRelationService;

    public List<ItemSetThemeResp> getItemSetThemeList() {
        List<ItemSetTheme> themeList = getBaseMapper().selectList(
                Wrappers.<ItemSetTheme>lambdaQuery()
                        .eq(ItemSetTheme::getValid, ValidEnum.VALID.getValue())
                        .orderByDesc(ItemSetTheme::getSort));

        return themeList.stream().map(theme -> {
            ItemSetThemeResp themeResp = new ItemSetThemeResp();
            BeanUtils.copyProperties(theme, themeResp);
            return themeResp;
        }).collect(Collectors.toList());
    }

    public Map<Long, ItemSetThemeVo> getThemeBySpuIds(Collection<Long> spuIds) {
        //查询spu和主题的关系
        List<ItemSetThemeSpuRelation> themeRelationList = itemSetThemeSpuRelationService.list(
                Wrappers.<ItemSetThemeSpuRelation>lambdaQuery()
                        .in(ItemSetThemeSpuRelation::getSpuId, spuIds)
        );

        Map<Long, Long> themeRelationMap = themeRelationList.stream()
                .collect(Collectors
                        .toMap(ItemSetThemeSpuRelation::getSpuId, ItemSetThemeSpuRelation::getThemeId, (id1, id2) -> id1));

        List<ItemSetTheme> itemSetThemes = listByIds(new HashSet<>(themeRelationMap.values()));

        Map<Long, ItemSetTheme> themeMap = itemSetThemes.stream()
                .collect(Collectors.toMap(ItemSetTheme::getId, theme -> theme, (theme1, theme2) -> theme1));

        Map<Long, ItemSetThemeVo> spuThemeMap=new HashMap<>();

        themeRelationMap.forEach((spuId, themeId) -> {
            ItemSetTheme itemSetTheme = themeMap.get(themeId);
            ItemSetThemeVo itemSetThemeVo =new ItemSetThemeVo();
            itemSetThemeVo.setThemeId(themeId);
            BeanUtils.copyProperties(itemSetTheme, itemSetThemeVo);
            spuThemeMap.put(spuId, itemSetThemeVo);
        });

        return spuThemeMap;

    }
}
