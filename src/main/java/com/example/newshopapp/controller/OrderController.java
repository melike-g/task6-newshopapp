package com.example.newshopapp.controller;

import com.example.newshopapp.entity.Cart;
import com.example.newshopapp.entity.CustomerOrder;
import com.example.newshopapp.service.CartService;
import com.example.newshopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @PostMapping("/place/{cartId}")
    public ResponseEntity<CustomerOrder> placeOrder(@PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId)
                .orElseThrow(() -> new RuntimeException("Sepet bulunamadı"));
        CustomerOrder order = orderService.placeOrder(cart);
        cartService.emptyCart(cart); // sipariş sonrası sepet boşalır
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<CustomerOrder> getOrderByCode(@PathVariable String orderCode) {
        CustomerOrder order = orderService.getOrderByCode(orderCode);
        return ResponseEntity.ok(order);
    }
}
