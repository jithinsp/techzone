package com.ecommerce.techzone.admin.repository;

import com.ecommerce.techzone.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
}