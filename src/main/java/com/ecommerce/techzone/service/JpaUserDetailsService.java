package com.ecommerce.techzone.service;

import com.ecommerce.techzone.entity.SecurityUser;
import com.ecommerce.techzone.admin.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String firstname) throws UsernameNotFoundException {
        return userRepository
                .findByfirstname(firstname)
                .map(user -> new SecurityUser())
                .orElseThrow(()->new UsernameNotFoundException("Not found"));
    }
}


//SecurityUser::new <====> username -> new SecurityUser(userRepository.findByfirstname(username))