package com.projectcuoiky.atbm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItem_id")
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartItem() {
    }

    public CartItem(int id, Product product, @NotNull int quantity, User user) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", product='" + getProduct() + "'" + ", quantity='" + getQuantity() + "'"
                + ", user='" + getUser() + "'" + "}";
    }

}
