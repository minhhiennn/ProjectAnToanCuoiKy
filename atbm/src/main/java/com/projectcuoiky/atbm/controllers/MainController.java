package com.projectcuoiky.atbm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping(value = {"", "/", "home"})
    public String Home() {
        return "index";
    }

    @RequestMapping("/cart")
    public String CartItemList() {
        return "cart";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @RequestMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @RequestMapping("/productDetail")
    public String productDetail() {
        return "product-detail";
    }

    @RequestMapping("/shop-list-full")
    public String shopListFull() {
        return "shop-list-full";
    }
}
