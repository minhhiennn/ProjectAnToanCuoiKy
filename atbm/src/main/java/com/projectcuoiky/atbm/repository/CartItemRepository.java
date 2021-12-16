package com.projectcuoiky.atbm.repository;

import java.util.List;

import com.projectcuoiky.atbm.entities.CartItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAll();

    Page<CartItem> findAll(Pageable pageable);

    CartItem findCartItemById(Integer id);

}
