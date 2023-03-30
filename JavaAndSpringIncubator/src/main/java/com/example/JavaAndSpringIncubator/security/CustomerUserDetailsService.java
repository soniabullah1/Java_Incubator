//package com.example.JavaAndSpringIncubator.security;
//
//import com.example.JavaAndSpringIncubator.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class CustomerUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private final UserRepository userRepository;
//
//    public CustomerUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails userDetails = userRepository.findByUsername(username)
//                .stream()
//                .findFirst()
//                .map(customerEntity -> User.withUsername(customerEntity.getUsername())
//                        .password(customerEntity.getPassword())
//                        .roles("Admin")
//                        .build())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return userDetails;
//    }
//}
