package com.example.demo.rest.user;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@Slf4j
@RequestMapping("/api/v1")
public class UserRestCotroller {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Iterable<UserDto> getUsers(@RequestParam(name="msg", required = false, defaultValue = "World!") String param) {
        return this.userService.getUsers();
    }

    @GetMapping("/users/{name}")
    public UserDto getUser(@PathVariable String name) {
        return this.userService.getUserByName(name);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String,String>> postMethodName(@RequestBody @Valid UserDto body) {
        String name = body.name;
        String email = body.email;
        String password = body.password;
        UserDto user = new UserDto(name, email, password);
        if(this.userService.addUser(user)){
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User created"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "User already exists"));
        }
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Map<String,String>> putMethodName(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String password = body.get("password");
        log.info("User info in controller: " + id + " " + email + " " + password);
        UserDto user = new UserDto(name, email, password);
        if(this.userService.updateUserById(id, user)){
            return ResponseEntity.status(202).body(Map.of("message", "User updated"));
        }else{
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
    }

    @PatchMapping("users/{id}")
    public ResponseEntity<Map<String,String>> patchMethodName(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String password = body.get("password");
        UserDto user = new UserDto(name, email, password);
        if(this.userService.patchUserById(id, user)){
            return ResponseEntity.status(202).body(Map.of("message", "User updated"));
        }else{
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Map<String,String>> deleteMethodName(@PathVariable Long id) {
        UserDto user = this.userService.getUserById(id);
        if(user != null){
            this.userService.removeUserById(id);
            return ResponseEntity.status(200).body(Map.of("message", "User deleted"));
        }else{
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
    }
    
}
