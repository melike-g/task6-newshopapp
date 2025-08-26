package com.example.newshopapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;  // ürün adedi

    @Column(nullable = false)
    private Double priceAtAddition; // ürünü sepete eklerkenki fiyat

    @Column(nullable = false)
    private Double totalPrice; // quantity * priceAtAddition
}
