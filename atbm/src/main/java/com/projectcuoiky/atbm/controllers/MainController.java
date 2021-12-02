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
}
