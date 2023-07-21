package com.ecommerce.techzone;

import com.ecommerce.techzone.admin.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;
    @Test
    public void createRoles(){
 }
}
