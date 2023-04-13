package com.example.JavaAndSpringIncubator.repositories;

import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserID(Integer userID);
}
