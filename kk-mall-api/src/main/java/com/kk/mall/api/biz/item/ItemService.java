package com.kk.mall.api.biz.item;

import com.kk.mall.api.biz.item.product.ItemProductService;
import com.kk.mall.api.query.PageQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenwenkun
 */
@Service
public class ItemService {
    @Resource
    private ItemProductService itemProductService;

    public List<ItemListRepresentation> geItemHomeList(PageQuery pageQuery) {
        List<ItemListVo> itemListVos = itemProductService.geItemHomeList(pageQuery);
        return ItemListRepresentation.ofList(itemListVos);
    }

    public ItemDetailRepresentation getItemDetail(Long itemProductId) {
        ItemDetailVo itemDetail = itemProductService.getItemDetail(itemProductId);
        return ItemDetailRepresentation.of(itemDetail);
    }
}
