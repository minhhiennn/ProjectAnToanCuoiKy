package com.projectcuoiky.atbm.service;

import java.util.List;

import com.projectcuoiky.atbm.entities.Product;
import com.projectcuoiky.atbm.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return this.productRepository.findAll();
    }

}
