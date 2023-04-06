package com.example.JavaAndSpringIncubator;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleResponse {
    @JsonProperty("role")
    private String role;

    public RoleResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
