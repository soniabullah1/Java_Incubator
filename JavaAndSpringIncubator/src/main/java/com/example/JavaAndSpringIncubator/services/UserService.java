package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.CartItemDTO;
import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.User;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import com.example.JavaAndSpringIncubator.repositories.UserRepository;
import com.example.JavaAndSpringIncubator.security.CustomerUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final ObjectMapper objectMapper;
    private final CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    public UserService(UserRepository userRepository, CustomerUserDetailsService customerUserDetailsService) {
        this.userRepository = userRepository;
//        this.objectMapper = objectMapper;
        this.customerUserDetailsService = customerUserDetailsService;
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

//        // Generate a random salt
//        byte[] salt = new byte[16];
//        new SecureRandom().nextBytes(salt);

        // Generate a salt based on the user's username
        String salt = newUser.getUsername();

        // Concatenate password and salt
        String saltedPassword = newUser.getPassword() + salt;

        // Hash the password with the salt
        String hashedPassword = passwordEncoder.encode(saltedPassword);

        // Save the user with the hashed password and salt
//        UserDTO user = newUser.toEntity();
        newUser.setSalt(salt);
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser.toEntity());

        return UserStatus.SUCCESSFUL;
    }

    public UserDTO loginUser(UserDTO user) {
//        UserDTO storedUser = userRepository.findByUsername(user.getUsername());
//        UserDTO storedUser = (UserDTO) userRepository.findByUsername(user.getUsername());
        UserDetails storedUser = customerUserDetailsService.loadUserByUsername(user.getUsername());
//        UserDTO storedUser = objectMapper.toDTO((User) userRepository.findByUsername(user.getUsername()));

        if (storedUser != null) {
            String saltedPassword = user.getPassword() + user.getUsername();

            if (passwordEncoder.matches(saltedPassword, storedUser.getPassword())) {
                user.setIsLoggedIn(true);
                //userRepository.save(user.toEntity()); // Use save method to update the user record
                logger.info("userStatus: " + user.getIsLoggedIn());
                return UserDTO.fromEntity(user.toEntity(), user.getIsLoggedIn());
            }

        }

//        return UserStatus.UNSUCCESSFUL;
        return null;
    }
}

