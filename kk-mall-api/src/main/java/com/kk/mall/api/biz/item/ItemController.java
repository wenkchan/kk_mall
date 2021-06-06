package com.kk.mall.api.biz.item;

import com.kk.mall.api.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenwenkun
 */
@RestController
@RequestMapping("item")
@Api(tags = "产品相关")
public class ItemController {
    @Resource
    private ItemService itemService;

    @ApiOperation("获取首页产品列表")
    @GetMapping("home/list")
    public List<ItemListRepresentation> getItemHomeList(PageQuery pageQuery){
        return itemService.geItemHomeList(pageQuery);
    }
    @ApiOperation("获取产品详情")
    @GetMapping("detail/{itemProductId}")
    public ItemDetailRepresentation getItemDetail(@PathVariable Long itemProductId){
        return  itemService.getItemDetail(itemProductId);
    }

    @ApiOperation("获取搜索产品列表")
    @GetMapping("search/list")
    public ItemListRepresentation getItemSearchList(){
        return null;
    }


}
