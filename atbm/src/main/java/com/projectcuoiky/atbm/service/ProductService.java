package com.projectcuoiky.atbm.service;

import java.util.List;

import com.projectcuoiky.atbm.entities.Product;
import com.projectcuoiky.atbm.repository.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product findProductById(Integer id) {
        Product product = productRepository.findProductById(id);
        return product;
    }


}
