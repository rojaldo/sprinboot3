package com.example.demo.rest.library.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserError implements UserResponse{
    private int status;
    private String message;

}
