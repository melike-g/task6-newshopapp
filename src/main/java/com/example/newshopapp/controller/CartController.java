package com.example.newshopapp.controller;

import com.example.newshopapp.entity.Cart;
import com.example.newshopapp.entity.Product;
import com.example.newshopapp.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }


    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        return cartService.getCart(cartId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/{cartId}/addProduct")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long cartId,
            @RequestBody Product product,
            @RequestParam int quantity) {
        Cart updatedCart = cartService.addProductToCart(cartId, product, quantity);
        return ResponseEntity.ok(updatedCart);
    }


    @DeleteMapping("/{cartId}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId).map(cart -> {
            cartService.emptyCart(cart);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
