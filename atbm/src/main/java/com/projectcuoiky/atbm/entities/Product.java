package com.projectcuoiky.atbm.entities;

<<<<<<< HEAD
import javax.persistence.*;
=======
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
>>>>>>> f97533d3e0543594be3e758e2b254d933443494f
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @NotNull
    @Column(name = "productName")
    private String name;

    @NotNull
    @Column(name = "productPrice")
    private double price;

    @Column(name = "productDiscountPrice")
    private Double discountPrice;

    @NotNull
    @Column(name = "inStock")
    private int inStock;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<CartItem> cartItems = new HashSet<>();

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

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Product() {
    }

    public Product(int id, @NotNull String name, @NotNull double price, Double discountPrice, @NotNull int inStock,
            Set<CartItem> cartItems) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.inStock = inStock;
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", price='" + getPrice() + "'"
                + ", discountPrice='" + getDiscountPrice() + "'" + ", inStock='" + getInStock() + "'"
                + "}";
    }

}
