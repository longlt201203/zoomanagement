package com.nhom3.zoomanagement.testAuth;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String id;

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }
}
