package com.example.demo.rest.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDto {
    @NotBlank
    public String name;
    @Email
    public String email;
    // admit only digits of size between 2 and 5
    @Pattern(regexp = "\\d{2,5}")
    public String password;

    public UserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
