package com.example.demo.rest.user;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
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
    public Map<String,String> postMethodName(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String password = body.get("password");
        User user = new User(name, email, password);
        this.userService.addUser(user);
        return Map.of("message", "User added");
    }
    
    
}
