package com.kk.mall.api.biz.commodity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenwenkun
 */
@RestController
@RequestMapping("/commodity")
@Api(tags = "商品相关")
public class CommodityController {

    @ApiOperation("获取首页商品列表")
    @GetMapping("home/list")
    public CommodityListRepresentation getCommodityHomeList(){
        return null;
    }

    @ApiOperation("获取搜索商品列表")
    @GetMapping("search/list")
    public CommodityListRepresentation getCommoditySearchList(){
        return null;
    }

    @ApiOperation("获取商品详情")
    @GetMapping("detail/{commodityId}")
    public CommodityDetailRepresentation getCommodityDetail(@PathVariable Long commodityId){
        return null;
    }
}
