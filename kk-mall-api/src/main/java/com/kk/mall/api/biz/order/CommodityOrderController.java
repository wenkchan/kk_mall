package com.kk.mall.api.biz.order;

import org.springframework.web.bind.annotation.*;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/6/24 4:33 下午
 */
@RestController
@RequestMapping("commodity/order")
public class CommodityOrderController {

    @GetMapping("list")
    public void getCommodityOrderList() {

    }

    @GetMapping("search")
    public void searchCommodityOrder() {

    }

    @GetMapping("detail/{commodityOrderId}")
    public void getCommodityOrderDetail(@PathVariable long commodityOrderId) {

    }

    @PostMapping("creation")
    public void createCommodityOrder() {

    }

    @DeleteMapping("deletion")
    public void deleteCommodityOrder() {

    }
}

