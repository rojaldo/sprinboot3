package com.example.demo.rest.user;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Slf4j
public class UserRestCotroller {

    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public Iterable<User> getUsers(@RequestParam(name="msg", required = false, defaultValue = "World!") String param) {
        return this.userService.getUsers();
    }

    @GetMapping("/users/{name}")
    public User getUser(@PathVariable String name) {
        return this.userService.getUser(name);
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String,String>> postMethodName(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String password = body.get("password");
        User user = new User(name, email, password);
        if(this.userService.addUser(user)){
            return ResponseEntity.status(201).body(Map.of("message", "User created"));
        }else {
            return ResponseEntity.status(400).body(Map.of("message", "User already exists"));
        }
    }

    @PutMapping("users/{name}")
    public ResponseEntity<Map<String,String>> putMethodName(@PathVariable String name, @RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        log.info("User info in controller: " + name + " " + email + " " + password);
        User user = new User(name, email, password);
        if(this.userService.updateUser(user)){
            return ResponseEntity.status(202).body(Map.of("message", "User updated"));
        }else{
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
    }

    @DeleteMapping("users/{name}")
    public ResponseEntity<Map<String,String>> deleteMethodName(@PathVariable String name) {
        User user = this.userService.getUser(name);
        if(user != null){
            this.userService.removeUser(user);
            return ResponseEntity.status(200).body(Map.of("message", "User deleted"));
        }else{
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
    }
    
}
