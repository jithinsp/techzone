//package com.ecommerce.techzone.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.stream.Collectors;
//
//@Component
//@NoArgsConstructor
//public class SecurityUser implements UserDetails {
//
//    public SecurityUser(User user,Admin admin) {
//        super();
//        this.user = user;
//        this.admin=admin;
//    }
//
//    private User user;
//    private Admin admin;
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (user instanceof User) {
//            return Arrays.stream(((User) user)
//                            .getUserRoles()
//                            .split(","))
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//        } else if (admin instanceof Admin) {
//            return Arrays.stream(((Admin) admin)
//                            .getAdminRoles()
//                            .split(","))
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getFirstname();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
