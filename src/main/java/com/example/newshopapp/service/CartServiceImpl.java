package com.example.newshopapp.service;

import com.example.newshopapp.entity.Cart;
import com.example.newshopapp.entity.Product;
import com.example.newshopapp.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();        // <-- eklendi
    }

    @Override
    public Optional<Cart> getCart(Long cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Cart addProductToCart(Long cartId, Product product, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));
        cart.addProduct(product, quantity);
        return cartRepository.save(cart);
    }

    @Override
    public void emptyCart(Cart cart) {
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);
    }
}
