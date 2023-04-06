package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.User;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDTO> getUsers();

    UserDTO getUserByID(Integer userID);

    UserStatus registerUser(UserDTO newUser);

    UserDTO loginUser(UserDTO user);

    UserStatus deleteUser(Integer userID);

    UserDTO updateUser(UserDTO userDTO, Integer userID);
}
