package com.ecommerce.techzone;

import com.ecommerce.techzone.entity.user.Role;
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

//    @Autowired
//    RoleRepository roleRepository;
//    @Test
//    public void createRoles(){
////        Role roleAdmin = new Role(UserRoles.ADMIN,"manage everything");
////        Role roleUser = new Role(UserRoles.USER,"browse and buy everything");
////        roleRepository.saveAll(List.of(roleAdmin,roleUser));
//    }
}
