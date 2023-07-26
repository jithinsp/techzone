package com.ecommerce.techzone.entity.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "user_id")
    private UUID uuid;

    @Column(unique=true)
    @NotNull(message = "username is empty")
    private String username;

    @Column(length = 128, nullable = false, unique = true)
    @Email(message = "invalid email")
    private String email;

    @Column(name = "is_deleted")
    private boolean isdeleted=false;

    private boolean enabled=true;

    @Column(name = "firstname", length = 45)
    @NotNull(message = "First name is empty")
    private String firstName;

    @Column(name = "lastname", length = 45)
    private String lastName;

    @Column(length = 64)
    @NotNull(message = "password is empty")
    private String password;

    @Column(unique = true)
    private String phone;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    private boolean verified = true;

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getName(){
        return firstName + " " +lastName;
    }

}
//OK