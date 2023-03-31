package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.CartItemDTO;
import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.User;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import com.example.JavaAndSpringIncubator.repositories.UserRepository;
import com.example.JavaAndSpringIncubator.services.CartService;
import com.example.JavaAndSpringIncubator.services.UserService;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    Logger logger = LogManager.getLogger(UserController.class.getName());

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<UserStatus> registerUser(@RequestBody UserDTO user) {
        UserStatus userStatus = userService.registerUser(user);
        return new ResponseEntity<>(userStatus, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<UserStatus> loginUser(@Valid @RequestBody UserDTO user) {
        UserStatus userDTO = userService.loginUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }

}
