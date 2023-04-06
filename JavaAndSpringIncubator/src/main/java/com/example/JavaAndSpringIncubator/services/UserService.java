package com.example.JavaAndSpringIncubator.services;

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
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService{

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


    public List<UserDTO> getUsers()
    {
        List<UserDTO> users = UserDTO.toDTOs(userRepository.findAll());
        return users;
    }

    public UserDTO getUserByID(Integer userID)
    {
        Optional<User> users = userRepository.findById(userID);

        return UserDTO.fromEntity(users.get(), false);
    }

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
        UserDetails storedUser = customerUserDetailsService.loadUserByUsername(user.getUsername());
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

    public UserStatus deleteUser(Integer userID)
    {
        Optional<User> users = userRepository.findById(userID);
        if(users.isPresent())
        {
            userRepository.delete(users.get());
            return UserStatus.SUCCESSFUL;
        }
        else
        {
            return UserStatus.UNSUCCESSFUL;
        }
    }

    public UserDTO updateUser(UserDTO userDTO, Integer userID)
    {
        Optional<User> user = userRepository.findById(userID);
        User userEntity = user.get();

        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setRole(userDTO.getRole());

        userRepository.save(userEntity);
        return userDTO;
    }
}

