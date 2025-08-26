package com.example.newshopapp.service;

import com.example.newshopapp.entity.Cart;
import com.example.newshopapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> getAllCarts();                    // <-- eklendi
    Optional<Cart> getCart(Long cartId);
    Cart addProductToCart(Long cartId, Product product, int quantity);
    void emptyCart(Cart cart);
}
