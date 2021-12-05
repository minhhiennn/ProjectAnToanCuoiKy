package com.projectcuoiky.atbm.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@PreAuthorize("isAuthenticated()")
public class MainController {

    @PreAuthorize("hasRole('ROLE_User')")
    @RequestMapping(value = { "/", "home"})
    public String Home() {
        return "views/Home";
    }

    @PreAuthorize("hasAnyRole('ROLE_User','ROLE_Admin')")
    @RequestMapping("/cart")
    public String CartItemList() {
        return "views/Cart";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/signup")
    public String signUp() {
        return "views/Signup";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/signin")
    public String signIn() {
        return "views/Signin";
    }

    @PreAuthorize("hasAnyRole('ROLE_User','ROLE_Admin')")
    @RequestMapping("/productDetail")
    public String productDetail() {
        return "views/product-detail";
    }

    @PreAuthorize("hasAnyRole('ROLE_User','ROLE_Admin')")
    @RequestMapping("/shop")
    public String shopListFull() {
        return "views/Shop";
    }

}
