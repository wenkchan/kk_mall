package com.kk.mall.api.biz.commodity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/6/24 3:20 下午
 */
@RestController
@RequestMapping("commodity")
public class CommodityController {

    @GetMapping("category/list")
    public void getCategoryList() {

    }

    @GetMapping("list")
    public void getCommodityList(){

    }

    @GetMapping("search")
    public void searchCommodity(){

    }

    @GetMapping("detail/{commodityId}")
    public void getCommodityDetail(@PathVariable("commodityId") long commodityId){

    }


}

