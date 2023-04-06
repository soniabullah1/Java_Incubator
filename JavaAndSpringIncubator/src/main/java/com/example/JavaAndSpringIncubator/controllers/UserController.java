package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.RoleResponse;
import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import com.example.JavaAndSpringIncubator.repositories.UserRepository;
import com.example.JavaAndSpringIncubator.services.IUserService;
import com.example.JavaAndSpringIncubator.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService iUserService;
    Logger logger = LogManager.getLogger(UserController.class.getName());

    private final UserRepository userRepository;


    @Autowired
    public UserController(UserService iUserService, UserRepository userRepository) {
        this.iUserService = iUserService;
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers()
    {
        List<UserDTO> users = iUserService.getUsers();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable Integer userID)
    {
        UserDTO user = iUserService.getUserByID(userID);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/role/{username}")
    public ResponseEntity<RoleResponse> getRoleByUsername(@PathVariable String username)
    {
        String role = iUserService.getRoleByUsername(username);
        RoleResponse response = new RoleResponse(role);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<UserStatus> registerUser(@RequestBody UserDTO user) {
        UserStatus userStatus = iUserService.registerUser(user);
        return new ResponseEntity<>(userStatus, HttpStatus.ACCEPTED);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO user) {
        UserDTO userDTO = iUserService.loginUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<UserStatus> deleteUser (@PathVariable Integer userID) {
        UserStatus userStatus = iUserService.deleteUser(userID);
        return new ResponseEntity<>(userStatus, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/update/{userID}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable Integer userID)
    {
        UserDTO user = iUserService.updateUser(userDTO, userID);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

}
