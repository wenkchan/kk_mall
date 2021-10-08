package com.kk.mall.api.biz.item;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kk.mall.api.consts.SwaggerGroup;
import com.kk.mall.api.query.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author chenwenkun
 */
@RestController
@RequestMapping("item")
@Api(tags = SwaggerGroup.SET)
public class ItemController {
    @Resource
    private ItemFacadeService itemFacadeService;

    @ApiOperation("获取套组列表")
    @GetMapping("/list")
    public Page<ItemListResp> getItemHomeList(PageQuery pageQuery){
        return itemFacadeService.getItemHomeList(pageQuery);
    }
    @ApiOperation("获取套组详情")
    @GetMapping("detail")
    public ItemDetailResp getItemDetail(@RequestParam Long itemId){
        return  itemFacadeService.getItemDetail(itemId);
    }
//
//    @ApiOperation("获取搜索产品列表")
//    @GetMapping("search/list")
//    public ItemListRepresentation getItemSearchList(){
//        return null;
//    }


}
