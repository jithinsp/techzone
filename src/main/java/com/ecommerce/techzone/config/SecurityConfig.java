package com.ecommerce.techzone.config;

import com.ecommerce.techzone.service.user.UserInfoProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    //CONFIGURING USERS
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("root")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ROLE_ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ROLE_USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin,user);

        return new UserInfoProviderService();
    }

    @Autowired
    PasswordEncoderConfig passwordEncoderConfig;

    //CONFIGURING ACCESS
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().permitAll() // Permit all requests
                .authorizeHttpRequests()
                .antMatchers("/signup", "/users/save", "/app/*","/*.css","*.scss")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/app/**")
                .authenticated().and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403") // Custom forbidden error page
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        //giving info about who is the user details service and password encoder
        //these info can be used to generate user details object and set it to authentication object.
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoderConfig.passwordEncoder());

        return authenticationProvider;
    }

}
