//package com.ecommerce.techzone.configuration;
//
//
//import com.ecommerce.techzone.service.JpaUserDetailsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    @Autowired
//    JpaUserDetailsService jpaUserDetailsService;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests((authorize)->authorize
//                        .requestMatchers("/user/user_signup").permitAll()
//                        .requestMatchers("/user/user_home").permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .userDetailsService(jpaUserDetailsService)
//                .formLogin()
//                .loginPage("/user/user_login")
//                .permitAll();
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(jpaUserDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//}
