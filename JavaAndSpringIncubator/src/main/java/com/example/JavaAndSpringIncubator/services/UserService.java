package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.User;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import com.example.JavaAndSpringIncubator.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    Logger logger = LogManager.getLogger(UserService.class.getName());


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

    public UserStatus loginUser(UserDTO user) {
//        UserDTO storedUser = userRepository.findByUsername(user.getUsername());
        UserDTO storedUser = (UserDTO) UserDTO.toDTOs((List<User>) userRepository.findByUsername(user.getUsername()));

        if (storedUser != null) {
            String saltedPassword = user.getPassword() + storedUser.getSalt();
            String hashedPassword = passwordEncoder.encode(saltedPassword);

            if (hashedPassword.equals(storedUser.getPassword())) {
                storedUser.setLoggedIn(true);
                userRepository.save(storedUser.toEntity());
                return UserStatus.SUCCESSFUL;
            }
        }

        return UserStatus.UNSUCCESSFUL;
    }
}

