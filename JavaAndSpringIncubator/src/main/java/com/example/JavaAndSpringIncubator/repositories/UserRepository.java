package com.example.JavaAndSpringIncubator.repositories;

import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    List<User> findByUsername(String Username);

    User findByUsername(String Username);
    public User save(User user);
}
