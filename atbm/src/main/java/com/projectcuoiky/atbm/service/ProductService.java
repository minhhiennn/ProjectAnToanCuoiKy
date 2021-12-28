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

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public List<Product> getAllListProductByPage(int page, int size, String sortName, boolean desc) {
        List<Product> productList;
        Pageable paging = null;
        if (sortName == null) {
            paging = PageRequest.of(page, size);
        } else if (sortName != null & desc == false) {
            paging = PageRequest.of(page, size, Sort.by(sortName));
        } else if (sortName != null & desc == true) {
            paging = PageRequest.of(page, size, Sort.by(sortName).descending());
        }
        Page<Product> pageProducts = productRepository.findAll(paging);
        productList = pageProducts.getContent();
        return productList;
    }

    public int getMaxPage(int size) {
        int countAllProduct = productRepository.findAll().size();
        if (countAllProduct % size == 0) {
            return (countAllProduct / size);
        } else {
            return (countAllProduct / size) + 1;
        }
    }
}
