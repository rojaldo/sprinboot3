package com.example.demo.rest.library.users;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import com.example.demo.rest.library.lends.LendEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;
    
    @NonNull
    @Length(min = 1, max = 100)
    private String firstName;

    @NonNull
    @Length(min = 1, max = 100)
    private String lastName;

    @Transient
    @Value("#{this.firstName + ' ' + this.lastName}")
    private String fullName;

    @NonNull
    @Email
    @Column(unique = true, nullable = false, length = 100, columnDefinition = "VARCHAR(100)", updatable = true, name = "email")
    private String email;

    @NonNull
    @Range(min = 0, max = 150)
    private int age;

    @NonNull
    @Pattern(regexp = "^[0-9]{8}[A-Z]$")
    @Column(unique = true, nullable = false, length = 9, columnDefinition = "VARCHAR(9)", updatable = false, name = "dni")
    private String dniStr;

    @OneToMany(mappedBy = "user")
    private List<LendEntity> lends;

}
