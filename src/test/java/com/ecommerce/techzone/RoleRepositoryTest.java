package com.ecommerce.techzone;

import com.ecommerce.techzone.admin.repository.RoleRepository;
import com.ecommerce.techzone.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;
    @Test
    public void createRoles(){
        Role roleAdmin = new Role("Admin","manage everything");
        Role roleUser = new Role("User","browse and buy everything");
        roleRepository.saveAll(List.of(roleAdmin,roleUser));
    }
}
