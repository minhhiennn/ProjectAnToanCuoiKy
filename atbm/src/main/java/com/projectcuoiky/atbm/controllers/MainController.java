package com.projectcuoiky.atbm.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.projectcuoiky.atbm.dtos.ProductDto;
import com.projectcuoiky.atbm.entities.Product;
import com.projectcuoiky.atbm.entities.User;
import com.projectcuoiky.atbm.service.ProductService;
import com.projectcuoiky.atbm.service.UserRegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    // @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping("/shop")
    // public String shopListFull(Model model) {
    //     List<Product> list = productService.getAllProduct();
    //     model.addAttribute("listProducts", list);//tuoi loz
    //     System.out.println(list);
    //     return "views/Shop1";
    // }
    public String shopPage(Model model, @RequestParam(required = false, name = "page") String Spage,
                           @RequestParam(required = false, name = "size") String Ssize,
                           @RequestParam(required = false, name = "sortBy") String sortBy,
                           @RequestParam(required = false, name = "desc") String Sdesc) {
        try {
            int page = (Spage == null ? 1 : Integer.parseInt(Spage));
            int size = (Ssize == null ? 12 : Integer.parseInt(Ssize));
            boolean desc = ((Sdesc != null && Sdesc.contains("true")) ? true : false);
            List<Product> listProducts = productService.getAllListProductByPage((page - 1), size, sortBy, desc);
            List<ProductDto> dtos = new ArrayList<>();

            for (Product product : listProducts) {
                dtos.add(new ProductDto(product.getId(), product.getName(), product.getPrice(),
                        product.getDiscountPrice(), product.getInStock()));
            }
            model.addAttribute("listProducts", dtos);
            model.addAttribute("pageMax", productService.getMaxPage(size));
            model.addAttribute("currentPage", page);
            model.addAttribute("shows", size);
            model.addAttribute("sortBy", (sortBy != null ? sortBy : "id"));
            model.addAttribute("desc", String.valueOf(desc));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
