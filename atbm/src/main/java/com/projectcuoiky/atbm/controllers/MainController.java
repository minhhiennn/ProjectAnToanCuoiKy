package com.projectcuoiky.atbm.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.projectcuoiky.atbm.dtos.ProductDto;
import com.projectcuoiky.atbm.entities.Product;
import com.projectcuoiky.atbm.entities.User;
import com.projectcuoiky.atbm.service.CartItemService;
import com.projectcuoiky.atbm.service.ProductService;
import com.projectcuoiky.atbm.service.UserRegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@PreAuthorize("isAuthenticated()")
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private CartItemService cartItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/", "home"})
    public String Home(HttpSession session) {
        return "views/Home";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping("/cart")
    public String CartItemList(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);
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
    @ResponseBody
    public String postSignUp(@ModelAttribute("user") User user, Model model, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        userRegisterService.register(user, getSiteURL(request));
        model.addAttribute("message", "wasSend");
        return "success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/signin")
    public String signIn(@RequestParam(value = "invalid-session", defaultValue = "false") boolean invalidSession, final Model model) {
        if (invalidSession == true) {
            model.addAttribute("invalidSession", "You already have an action session. We do not allow multiple active sessions");
        }
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
    public String verifyUser(@Param("code") String code, Model model) {
        if (userRegisterService.verify(code)) {
            return "redirect:/signin";
        } else {
            model.addAttribute("message", "verifyError");
            return "views/Signup";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam(required = true, name = "productId") int productId, @RequestParam(required = true, name = "userEmail") String userEmail) {
        cartItemService.addToCart(productId, userEmail, 1);
        return "success";
    }

}
