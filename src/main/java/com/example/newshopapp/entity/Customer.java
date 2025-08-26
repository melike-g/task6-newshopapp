package com.example.newshopapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Bir müşteri sadece 1 sepete sahip olabilir
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;
}
