package com.ecommerce.techzone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id")
    private UUID user_id;

    @Column(length = 128, nullable = false, unique = true)
    private String userEmail;

    @Column(name = "is_deleted")
    private boolean isdeleted=false;

    private boolean enabled=true;

    @Column(name = "firstname", length = 45, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 45, nullable = false)
    private String lastname;

    @Column(length = 64, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void setRoles(Set<Role> roles){
        this.roles=roles;
    }

    public User(String userEmail, String firstname, String lastname, String password) {
        this.userEmail = userEmail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }


}