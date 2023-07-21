package com.ecommerce.techzone.user.service;

import com.ecommerce.techzone.dto.CreateUserRequest;
import com.ecommerce.techzone.entity.User;
import com.ecommerce.techzone.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //Configuration class is in the Security package(it is using BCrypt)

    //getting the password and encoding it, then setting to the password field
    private void encodePassword(CreateUserRequest user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean save(CreateUserRequest createUserRequest){
        if (!isEmailUnique(createUserRequest.getUserEmail())){
            System.out.println("you are here, not unique");
            return false;
        }else if(createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
            System.out.println("you are here,unique");
            encodePassword(createUserRequest); //calling the above encode password method
            User newUser = User.builder()
                    .firstname(createUserRequest.getFirstname())
                    .lastname(createUserRequest.getLastname())
                    .userEmail(createUserRequest.getUserEmail())
                    .password(createUserRequest.getPassword())
                    .build();
            System.out.println("user: "+newUser);
        userRepository.save(newUser);
        return true;
        }
        return false;
    }


    //To check the uniqueness of the email
    public boolean isEmailUnique(String email){
        User userByEmail = userRepository.findByuserEmail(email);
        return userByEmail==null;
    }

}
