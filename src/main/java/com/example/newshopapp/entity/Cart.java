package com.example.newshopapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(nullable = false)
    private Double totalPrice = 0.0;

    // ✅ Ürün ekleme metodu
    public void addProduct(Product product, int quantity) {
        // Sepette bu ürün var mı kontrol et
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                recalculateTotalPrice();
                return;
            }
        }

        // Ürün yoksa yeni CartItem oluştur
        CartItem newItem = new CartItem();
        newItem.setCart(this);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        items.add(newItem);

        recalculateTotalPrice();
    }

    // ✅ Ürün çıkarma metodu
    public void removeProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() - quantity;
                if (newQuantity <= 0) {
                    items.remove(item); // tamamen çıkar
                } else {
                    item.setQuantity(newQuantity);
                }
                recalculateTotalPrice();
                return;
            }
        }
    }

    // ✅ Toplam fiyatı tekrar hesaplama
    private void recalculateTotalPrice() {
        totalPrice = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}

