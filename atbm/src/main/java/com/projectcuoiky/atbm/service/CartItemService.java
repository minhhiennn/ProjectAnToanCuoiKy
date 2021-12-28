package com.projectcuoiky.atbm.service;

import com.projectcuoiky.atbm.entities.CartItem;
import com.projectcuoiky.atbm.repository.CartItemRepository;

import com.projectcuoiky.atbm.repository.ProductRepository;
import com.projectcuoiky.atbm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getListCartByUserId(Integer id) {
        List<CartItem> list = cartItemRepository.findCartItemsByUserId(id);
        return list;
    }

    public void addToCart(int productId, String userEmail, int quantity) {
        int userId = userRepository.findByEmail(userEmail).getId();
        CartItem cartItem = cartItemRepository.findCartItemByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem1 = new CartItem();
            cartItem1.setUser(userRepository.findUserById(userId));
            cartItem1.setProduct(productRepository.findProductById(productId));
            cartItem1.setQuantity(quantity);
            cartItemRepository.save(cartItem1);
        }
    }

    public void deleteCartItem(int cartItemId){
        cartItemRepository.deleteById(Long.valueOf(cartItemId));
    }
}
