package com.projectcuoiky.atbm.repository;

import java.util.List;
import java.util.Set;

import com.projectcuoiky.atbm.entities.CartItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

//    List<CartItem> findAll();
//
//    Page<CartItem> findAll(Pageable pageable);
//
//    CartItem findCartItemById(Integer id);

    List<CartItem> findCartItemsByUserId(Integer userId);

    CartItem findCartItemByUserIdAndProductId(Integer userId, Integer productId);

}
