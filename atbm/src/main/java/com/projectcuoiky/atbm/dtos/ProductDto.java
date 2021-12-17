package com.projectcuoiky.atbm.dtos;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private Integer id;

    private String name;

    private double price;

    private Double discountPrice;

    private int inStock;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getInStock() {
        return this.inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public ProductDto(Integer id, String name, double price, Double discountPrice, int inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.inStock = inStock;

    }

}
