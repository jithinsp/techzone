package com.ecommerce.techzone.repository.admin;

import com.ecommerce.techzone.entity.user.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {

    @Query(value = "SELECT * FROM roles WHERE role_name = :name", nativeQuery = true)
    Optional<Role> findRoleByName(@Param("name") String name);
}
