package com.example.JavaAndSpringIncubator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Table(name = "CartItem")
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemID")
    private Integer cartItemID;

    @Column(name = "CartID")
    private Integer cartID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BookID", referencedColumnName = "BookID")
    private Books bookID;

    @Column(name = "Quantity")
    private Integer quantity;
}
