package com.example.demo.rest.library.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Iterable<UserDto> getAllUsers() {
        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        this.usersRepository.findAll().forEach(userEntities::add);
        return this.convertUserEntitiesToUserDtos(userEntities);
    }

    public UserDto getUserById(long id){
        UserEntity userEntity = this.usersRepository.findById(id);
        if (userEntity == null) {
            return null;
        }
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .age(userEntity.getAge())
                .dniStr(userEntity.getDniStr())
                .build();
    }

    Iterable<UserDto> convertUserEntitiesToUserDtos(List<UserEntity> userEntities) {
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (UserEntity ue : userEntities) {
            userDtos.add(UserDto.builder()
                    .id(ue.getId())
                    .email(ue.getEmail())
                    .firstName(ue.getFirstName())
                    .lastName(ue.getLastName())
                    .age(ue.getAge())
                    .dniStr(ue.getDniStr())
                    .build());
        }
        return userDtos;
    }

    public UserDto createUser(UserDto user) {
        System.out.println("user: " + user);
        UserEntity userEntity = UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .age(user.getAge())
                .dniStr(user.getDniStr())
                .build();
        this.usersRepository.save(userEntity);
        return user;
    }

    public UserDto modifyUser(UserDto user, long id) {
        UserEntity userEntity = this.usersRepository.findById(id);
        if (userEntity == null) {
            return null;
        }
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setAge(user.getAge());
        userEntity.setDniStr(user.getDniStr());
        this.usersRepository.save(userEntity);
        return user;
    }

    public UserDto deleteUserById(long id) {
        UserEntity userEntity = this.usersRepository.findById(id);
        if (userEntity == null) {
            return null;
        }
        this.usersRepository.delete(userEntity);
        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .age(userEntity.getAge())
                .dniStr(userEntity.getDniStr())
                .build();
    }

    public List<UserDto> findUsers(UserDto user) {
        List<UserEntity> userEntities = this.usersRepository.findByEmailOrDniStr(user.getEmail(), user.getDniStr());
        if (userEntities.size() == 0) {
            return null;
        }
        List <UserDto> userDtos = new ArrayList<UserDto>();
        for (UserEntity ue : userEntities) {
            userDtos.add(UserDto.builder()
                    .id(ue.getId())
                    .firstName(ue.getFirstName())
                    .lastName(ue.getLastName())
                    .email(ue.getEmail())
                    .age(ue.getAge())
                    .dniStr(ue.getDniStr())
                    .build());
        }
        return userDtos;
    }

}
