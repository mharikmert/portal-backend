package com.fikirtepe.app.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
