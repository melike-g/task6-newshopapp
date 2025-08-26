package com.example.newshopapp.service;

import com.example.newshopapp.entity.Cart;
import com.example.newshopapp.entity.CartItem;
import com.example.newshopapp.entity.CustomerOrder;
import com.example.newshopapp.entity.OrderItem;
import com.example.newshopapp.repository.CustomerOrderRepository;
import com.example.newshopapp.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerOrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public CustomerOrder placeOrder(Cart cart) {
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Sepet boş, sipariş verilemez");
        }

        CustomerOrder order = new CustomerOrder();
        order.setCustomer(cart.getCustomer());
        order.setOrderCode(UUID.randomUUID().toString());

        double totalPrice = 0.0;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getPriceAtAddition());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            order.getItems().add(orderItem);
            totalPrice += orderItem.getTotalPrice();
        }

        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public CustomerOrder getOrderByCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı"));
    }
}
