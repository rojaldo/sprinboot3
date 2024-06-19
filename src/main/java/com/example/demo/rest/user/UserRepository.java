package com.example.demo.rest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
    List<UserEntity> findByEmail(String email);
    List<UserEntity> findByNameAndEmail(String name, String email);
    List<UserEntity> findByNameOrEmail(String name, String email);
}
