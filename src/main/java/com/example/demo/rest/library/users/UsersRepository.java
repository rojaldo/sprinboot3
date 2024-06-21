package com.example.demo.rest.library.users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findById(long id);
    UserEntity findByEmail(String email);
    List<UserEntity> findByFirstName(String firstName);
    List<UserEntity> findByLastName(String lastName);
    List<UserEntity> findByEmailOrDniStr(String email, String dniStr);
}