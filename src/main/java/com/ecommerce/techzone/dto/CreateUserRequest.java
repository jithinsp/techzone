package com.ecommerce.techzone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String firstname;
    private String lastname;
    private String userEmail;
    private String password;
    private String confirmPassword;
}
