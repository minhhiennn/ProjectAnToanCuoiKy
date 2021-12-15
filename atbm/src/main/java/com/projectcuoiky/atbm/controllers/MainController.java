package com.projectcuoiky.atbm.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.projectcuoiky.atbm.entities.Product;
import com.projectcuoiky.atbm.entities.User;
import com.projectcuoiky.atbm.service.ProductService;
import com.projectcuoiky.atbm.service.UserRegisterService;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
//@PreAuthorize("isAuthenticated()")
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRegisterService userRegisterService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/", "home"})
    public String Home() {
        return "views/Home";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping("/cart")
    public String CartItemList() {
        return "views/Cart";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/signup")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "views/Signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String postSignUp(@ModelAttribute("user") User user, Model model, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        userRegisterService.register(user, getSiteURL(request));
        model.addAttribute("message","wasSend");
        return "success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/signin")
    public String signIn() {
        System.out.println("hello");
        return "views/Signin";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping("/productDetail")
    public String productDetail() {
        return "views/product-detail";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping("/shop")
    public String shopListFull(Model model) {
        List<Product> list = productService.getAllProduct();
        model.addAttribute("listProducts", list);//tuoi loz
        System.out.println(list);
        return "views/Shop";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code,Model model) {
        if (userRegisterService.verify(code)) {
            return "redirect:/signin";
        } else {
            model.addAttribute("message","verifyError");
            return "views/Signup";
        }
    }

}
