package com.example.demo.rest.library.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library")
public class UsersRestController {

    @Autowired
    UsersService usersService;

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserDto>> getUsers() {
        return ResponseEntity.status(200).body(this.usersService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Validated UserDto user) {
        List<UserDto> existingUsers = this.usersService.findUsers(user);
        if (existingUsers != null) {
            return ResponseEntity.status(409).body(
                UserError.builder()
                    .status(409)
                    .message("User already exists: " + existingUsers)
                    .build());
        }
        return ResponseEntity.status(201).body(this.usersService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> modifyUser(@RequestBody @Validated UserDto user, @PathVariable long id) {
        UserDto modifiedUser = this.usersService.modifyUser(user, id);
        if (modifiedUser == null) {
            return ResponseEntity.status(404).body(
                UserError.builder()
                    .status(404)
                    .message("User Id not found: " + id)
                    .build());
        } else {
            return ResponseEntity.status(200).body(modifiedUser);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable long id) {
        UserDto user = this.usersService.deleteUserById(id);
        if (user == null) {
            return ResponseEntity.status(404).body(
                UserError.builder()
                    .status(404)
                    .message("User Id not found: " + id)
                    .build());
        } else {
            return ResponseEntity.status(200).body(user);
        }
    }

}
