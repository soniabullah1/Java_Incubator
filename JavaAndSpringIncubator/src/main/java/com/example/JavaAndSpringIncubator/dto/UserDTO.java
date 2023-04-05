package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO {

    private Integer userID;

    private String username;

    private String password;

    private String salt;

    private String email;

    private LocalDate dateOfBirth;

    private String role;

    private Boolean isLoggedIn;

    public UserDTO() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (!(object instanceof UserDTO)){
            return false;
        }

        UserDTO userDTO = (UserDTO) object;
        return Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password);
    }

    public int hashCode() {
        return Objects.hash(userID, username, password, isLoggedIn);
    }

    public static UserDTO fromEntity(User user, Boolean status) {
        UserDTO userDTO = new UserDTO();
        userDTO.userID = user.getUserID();
        userDTO.username = user.getUsername();
        userDTO.password = user.getPassword();
        userDTO.email = user.getEmail();
        userDTO.dateOfBirth = user.getDateOfBirth();
        userDTO.role = user.getRole();
        userDTO.isLoggedIn = status;
        return userDTO;
    }

    @JsonIgnore
    public User toEntity() {
        User user = new User();
        user.setUserID(userID);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        user.setRole(role);

        return  user;
    }

    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + isLoggedIn +
                '}';
    }

    public static List<UserDTO> toDTOs(List<User> userEntities)
    {
        List<UserDTO> userDTO = new ArrayList<>();
        for(User user : userEntities)
        {
            userDTO.add(UserDTO.fromEntity(user, false));
        }

        return  userDTO;
    }
}
