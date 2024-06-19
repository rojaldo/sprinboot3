package com.example.demo.rest.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // private ArrayList<User> users = new ArrayList<User>();

    public boolean addUser(UserDto user){
        // check if email already exists
        List<UserEntity> userEntity = this.userRepository.findByEmail(user.email);
        if (userEntity.size() > 0) {
            log.info("User already exists");
            return false;
        }
        UserEntity userEntity2 = new UserEntity(user.name, user.email, user.password);
        this.userRepository.save(userEntity2);
        return true;
    }

    public List<UserDto> getUsers(){
        List<UserEntity> userEntities = this.userRepository.findAll();
        List<UserDto> users = new ArrayList<UserDto>();
        for (UserEntity userEntity : userEntities) {
            users.add(new UserDto(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword()));
        }
        return users;
    }

    public UserDto getUserById(long id){
        UserEntity userEntity = this.userRepository.findById(id).get();
        return new UserDto(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());
    }

    public void removeUserById(long id){
        this.userRepository.deleteById(id);
    }

    public void removeUser(UserDto user){
        // find user by email
    }

    // return user by name
    public UserDto getUserByName(String name){
        // find user by name
        List<UserEntity> userEntities = this.userRepository.findByName(name);
        if (userEntities.size() > 0) {
            UserEntity userEntity = userEntities.get(0);
            return new UserDto(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());
        }
        return null;
    }

    public boolean updateUserById(long id, UserDto user){
        UserEntity userEntity = this.userRepository.findById(id).get();
        if (userEntity != null) {
            userEntity.setName(user.name); // userEntity.setName(user.getName()
            userEntity.setEmail(user.email);
            userEntity.setPassword(user.password);
            this.userRepository.save(userEntity);
            return true;
        }
        return false;
    }

    public boolean patchUserById(long id, UserDto user){
        UserEntity userEntity = this.userRepository.findById(id).get();
        if (userEntity != null) {
            if (user.name != null) {
                userEntity.setName(user.name);
            }
            if (user.email != null) {
                userEntity.setEmail(user.email);
            }
            if (user.password != null) {
                userEntity.setPassword(user.password);
            }
            this.userRepository.save(userEntity);
            return true;
        }
        return false;
    }
    
}
