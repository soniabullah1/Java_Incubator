package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.User;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import com.example.JavaAndSpringIncubator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserStatus registerUser(UserDTO newUser) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.equals(newUser)) {
                return UserStatus.USER_ALREADY_EXISTS;
            }
        }

        // Generate a random salt
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        // Concatenate password and salt
        String saltedPassword = newUser.getPassword() + new String(salt);

        // Hash the password with the salt
        String hashedPassword = passwordEncoder.encode(saltedPassword);

        // Save the user with the hashed password and salt
//        UserDTO user = newUser.toEntity();
        newUser.setSalt(salt);
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser.toEntity());

        return UserStatus.SUCCESSFUL;
    }

//    public UserStatus loginUser(UserDTO userDTO) {
//        List<User> users = userRepository.findAll();
//
//        for (User other : users) {
//            if (other.equals(userDTO)) {
//                userDTO.setLoggedIn(true);
//                userRepository.save(userDTO.toEntity());
//                return UserStatus.SUCCESSFUL;
//            }
//        }
//        return UserStatus.UNSUCCESSFUL;
//    }

//    public UserStatus loginUser (UserDTO userDTO) {
//        UserDTO user = userLoginRepo.findByUsername(userDTO.getUsername());
//
//        if (user == null) {
//            return UserStatus.USER_NOT_FOUND;
//        }
//
//        // Concatenate password and stored salt
//        String saltedPassword = userDTO.getPassword() + new String(user.getSalt());
//
//        // Check if the password matches the stored hashed password
//        if (passwordEncoder.matches(saltedPassword, user.getPassword())) {
//            user.setLoggedIn(true);
//            return UserStatus.SUCCESSFUL;
//        } else {
//            return UserStatus.INVALID_PASSWORD;
//        }
//    }


}
