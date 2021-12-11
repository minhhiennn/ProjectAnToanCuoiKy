package com.projectcuoiky.atbm.repository;

import com.projectcuoiky.atbm.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    Page<Product> findAll(Pageable pageable);

    Product findProductById(Integer id);
    
}
