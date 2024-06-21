package com.example.demo.rest.library.users;

import org.hibernate.validator.constraints.Range;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserResponse{

    private long id;

    @NonNull
    @JsonProperty("first_name")
    private String firstName;

    @NonNull
    @JsonProperty("last_name")
    private String lastName;

    @Email
    private String email;

    @NonNull
    @Range(min = 0, max = 150)
    private int age;

    @JsonProperty("dni")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$")
    private String dniStr;
    
}
