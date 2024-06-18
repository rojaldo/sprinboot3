package com.example.demo.rest.user;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private ArrayList<User> users = new ArrayList<User>();

    public boolean addUser(User user){
        // check if user already exists
        for (User u : this.users) {
            if (u.email.equalsIgnoreCase(user.email)) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public void removeUser(int index){
        this.users.remove(index);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }

    // return user by name
    public User getUser(String name){
        for (User user : this.users) {
            // not case sensitive
            if (user.name.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUser(User user){
        for (User u : this.users) {
            if (u.name.equalsIgnoreCase(user.name)) {
                log.info( "User found: " + u.name);
                log.info( "User info: " + user.email + " " + user.password);
                u.email = user.email;
                u.password = user.password;
                log.info( "User info: " + u.email + " " + u.password);
                return true;
            }
        }
        return false;
    }
    
}
