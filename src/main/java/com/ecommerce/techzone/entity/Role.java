package com.ecommerce.techzone.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "role_id")
    private UUID id;
    @Column(length = 40, nullable = false, unique = true)
    private String name;
    @Column(length = 150, nullable = false)
    private String description;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
