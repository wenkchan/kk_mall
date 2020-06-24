package com.kk.mall.api.biz.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wenk.Chan
 * @version 1.0
 * @date 2020/6/24 3:30 下午
 */
@Controller
@RequestMapping("cart")
public class CartController {


    @GetMapping("list")
    public void getCartList() {

    }

    @PostMapping("commodity/addition")
    public void addToCart() {

    }

    @DeleteMapping("commodity/deletion")
    public void deleteFromCart() {

    }

    @PostMapping("commodity/quantity/alteration")
    public void alterCommodityQuantityInCart() {

    }


}

