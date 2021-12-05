package com.projectcuoiky.atbm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping(value = {"", "/", "home"})
    public String Home() {
        return "views/Home";
    }

    @RequestMapping("/cart")
    public String CartItemList() {
        return "views/Cart";
    }

    @RequestMapping("/signup")
    public String signUp() {
        return "views/Signup";
    }

    @RequestMapping("/signin")
    public String signIn() {
        return "views/Signin";
    }

    @RequestMapping("/productDetail")
    public String productDetail() {
        return "views/product-detail";
    }

    @RequestMapping("/shop")
    public String shopListFull() {
        return "views/Shop";
    }
}
