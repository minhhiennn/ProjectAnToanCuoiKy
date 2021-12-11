package com.projectcuoiky.atbm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private int id;

    @NotNull(message = "trường này không được để trống")
    @Column(name = "productName")
    private String name;

    @NotNull(message = "trường này không được để trống")
    @Column(name = "productPrice")
    private double price;

    @Column(name = "productDiscountPrice")
    private Double discountPrice;

    @NotNull(message = "trường này không được để trống")
    @Column(name = "inStock")
    private int inStock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Product() {
    }

    public Product(int id, @NotNull(message = "trường này không được để trống") String name,
            @NotNull(message = "trường này không được để trống") double price, Double discountPrice,
            @NotNull(message = "trường này không được để trống") int inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", price='" + getPrice() + "'"
                + ", discountPrice='" + getDiscountPrice() + "'" + ", inStock='" + getInStock() + "'"
                + "}";
    }

}