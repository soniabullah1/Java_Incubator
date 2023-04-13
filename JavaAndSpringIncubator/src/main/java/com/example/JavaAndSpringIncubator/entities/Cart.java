package com.example.JavaAndSpringIncubator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartID")
    private Integer cartID;

    @Column(name = "UserID")
    private Integer userID;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartID", referencedColumnName = "CartID")
    List<CartItem> cartItems;

}
