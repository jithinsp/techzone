package com.ecommerce.techzone.service.user;

import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.entity.user.UserInfoToUserDetailsConversion;
import com.ecommerce.techzone.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoProviderService implements UserDetailsService {
   @Autowired
   AdminRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //Getting user info from db.
      Optional<User> userInfo =   userInfoRepository.findByUsername(username);
      //intercept and handle otp login
//      if(userInfo.get().isOTPRequired()){
//          String password = userInfo.get().getPassword();
//
//      }

//      if (!userInfo.get().isEnabled()){
//          System.out.println(username+" is blocked");
//          throw new UsernameNotFoundException(username+" is blocked");
//      }else{
          //Converting user info to UserDetails so that it can be returned.
          return userInfo.map(UserInfoToUserDetailsConversion::new)
                  .orElseThrow(()-> new UsernameNotFoundException("user not found:"+username));
//      }

    }
}
