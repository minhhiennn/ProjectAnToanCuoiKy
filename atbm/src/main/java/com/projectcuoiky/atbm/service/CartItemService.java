package com.projectcuoiky.atbm.service;

import java.util.List;

import com.projectcuoiky.atbm.entities.CartItem;
import com.projectcuoiky.atbm.repository.CartItemRepository;

import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        return cartItems;
    }

    public CartItem findCartItemById(Integer id) {
        CartItem cartItem = cartItemRepository.findCartItemById(id);
        return cartItem;
    }

}
