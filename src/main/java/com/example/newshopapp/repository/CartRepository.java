package com.example.newshopapp.repository;

import com.example.newshopapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Burada ekstra query metodları yazabilirsin.
    // Örn: Belirli bir customerId'ye göre cart bulmak için:
    // Optional<Cart> findByCustomerId(Long customerId);

}
