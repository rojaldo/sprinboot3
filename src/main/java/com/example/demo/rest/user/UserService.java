package com.example.demo.rest.user;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private ArrayList<User> users = new ArrayList<User>();

    public void addUser(User user){
        this.users.add(user);
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }

    public void removeUser(int index){
        this.users.remove(index);
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
    
}
