package com.ecommerce.techzone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    UUID uuid;
    @NotNull(message = "Username is empty")
    private String username;
    @NotNull(message = "First Name is empty")
    private String firstName;
    @NotNull(message = "Last Name is empty")
    private String lastName;
    @NotNull(message = "password is empty")
    private String password;
    @NotNull(message = "password is empty")
    private String confirmPassword;

    @Email(message = "not a valid email")
    private String email;

    private String phone;
}
