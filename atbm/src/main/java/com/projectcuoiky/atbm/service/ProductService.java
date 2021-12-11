package com.projectcuoiky.atbm.service;

import com.projectcuoiky.atbm.repository.ProductRepository;

import org.springframework.stereotype.Service;
import com.projectcuoiky.atbm.entities.Product;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

    public Product findProductById(Integer id) {
        Product product = productRepository.findProductById(id);
        return product;
    }
    
}
