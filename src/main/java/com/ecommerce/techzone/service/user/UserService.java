package com.ecommerce.techzone.service.user;

import com.ecommerce.techzone.dto.CreateUserRequest;
import com.ecommerce.techzone.entity.user.OTP;
import com.ecommerce.techzone.entity.user.Role;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.admin.RoleRepository;
import com.ecommerce.techzone.repository.user.OtpRepository;
import com.ecommerce.techzone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //Configuration class is in the Security package(it is using BCrypt)

    //getting the password and encoding it, then setting to the password field
    private void encodePassword(CreateUserRequest user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean save(CreateUserRequest createUserRequest){
        System.out.println("inside saveService");
        Optional<Role> userRoleOptional = roleRepository.findRoleByName("ROLE_USER");
        Role userRole = userRoleOptional.orElseGet(()->{
            Role newRole = new Role();
            newRole.setRoleName("ROLE_USER");

            return roleRepository.save(newRole);
        });

//        userRepository.save(user);

        if (!isEmailUnique(createUserRequest.getEmail())){
            System.out.println("you are here, not unique");
            return false;
        }else if(createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
            System.out.println("you are here,unique");
            encodePassword(createUserRequest); //calling the above encode password method
            User newUser = User.builder()
                    .firstName(createUserRequest.getFirstName())
                    .lastName(createUserRequest.getLastName())
                    .username(createUserRequest.getUsername())
                    .email(createUserRequest.getEmail())
                    .password(createUserRequest.getPassword())
                    .phone(createUserRequest.getPhone())
                    .enabled(true)
                    .build();
            newUser.setRole(userRole);
            System.out.println("user: "+newUser);
        userRepository.save(newUser);
        return true;
        }
        return false;
    }

    //To check the uniqueness of the email
    public boolean isEmailUnique(String email){
        System.out.println("inside isEmailUnique");
        User userByEmail = userRepository.findByEmail(email);
        return userByEmail==null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveOTP(String otp,String username) {
//        Optional<User> user_db = userRepository.findById(id);
//        User updateUser = user_db.get();
        User user = findByUsername(username);
        OTP otps = new OTP();
        otps.setOtp(otp);
        otps.setUser(user);
        otpRepository.save(otps);
    }
}
