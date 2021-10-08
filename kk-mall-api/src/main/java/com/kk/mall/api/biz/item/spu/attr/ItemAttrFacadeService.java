package com.kk.mall.api.biz.item.spu.attr;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kk.mall.api.biz.item.spu.attr.key.ItemSpuAttrKey;
import com.kk.mall.api.biz.item.spu.attr.key.ItemSpuAttrKeyService;
import com.kk.mall.api.biz.item.spu.attr.relation.ItemSpuAttrRelation;
import com.kk.mall.api.biz.item.spu.attr.relation.ItemSpuAttrRelationService;
import com.kk.mall.api.biz.item.spu.attr.value.ItemSpuAttrValue;
import com.kk.mall.api.biz.item.spu.attr.value.ItemSpuAttrValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemAttrFacadeService {
    @Resource
    public ItemSpuAttrRelationService itemSpuAttrRelationService;

    @Resource
    public ItemSpuAttrKeyService itemSpuAttrKeyService;

    @Resource
    public ItemSpuAttrValueService itemSpuAttrValueService;


    public List<ItemAttrVo> getItemAttrBySpuIds(Collection<Long> spuIds){
        //查询spu和属性的关系
        List<ItemSpuAttrRelation> itemAttrSpuRelations =
                itemSpuAttrRelationService.list(Wrappers.<ItemSpuAttrRelation>lambdaQuery().in(ItemSpuAttrRelation::getSpuId,spuIds));

        List<Long> attrValueIdList = itemAttrSpuRelations.stream().map(ItemSpuAttrRelation::getAttrValueId)
                .collect(Collectors.toList());

        //查询属性值
        List<ItemSpuAttrValue> itemAttrValueList =
                itemSpuAttrValueService.listByIds(attrValueIdList);

        //查询属性key
        Map<Long, String> itemAttrKeyMap = itemAttrValueList.stream()
                .collect(Collectors.toMap(
                        ItemSpuAttrValue::getAttrKeyId,
                        ItemSpuAttrValue::getAttrValue,
                        (attr1, attr2) -> attr1));

        List<ItemSpuAttrKey> itemAttrKeys =
                itemSpuAttrKeyService.listByIds(itemAttrKeyMap.keySet());


        return ItemAttrVo.ofList(itemAttrKeys,itemAttrKeyMap);
    }
}
