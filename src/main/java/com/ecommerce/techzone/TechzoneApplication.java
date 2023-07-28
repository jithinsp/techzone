package com.ecommerce.techzone;

import com.ecommerce.techzone.entity.user.Role;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.admin.RoleRepository;
import com.ecommerce.techzone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class TechzoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechzoneApplication.class, args);
	}
}
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
//		return args -> {

//			Optional<Role> userRoleOptional = roleRepository.findRoleByName("ROLE_USER");
//			Role userRole = userRoleOptional.orElseGet(()->{
//				Role newRole = new Role();
//				newRole.setRoleName("ROLE_USER");
//
//				return roleRepository.save(newRole);
//			});
//			User user=User.builder().username("user")
//					.email("user@gmail.com")
//					.password(encoder.encode("1234"))
//					.roles("ROLE_USER")
//					.build();
// 			userRepository.save(user);

//			Optional<Role> adminRoleOptional = roleRepository.findRoleByName("ROLE_ADMIN");
//			Role adminRole = adminRoleOptional.orElseGet(() -> {
//				Role newRole = new Role();
//				newRole.setRoleName("ROLE_ADMIN");
//				return roleRepository.save(newRole);
//			});
//
//			User admin = User.builder().username("admin")
//					.firstName("Jithin")
//					.lastName("SP")
//					.email("admin@gmail.com")
//					.phone("8891860084")
//					.verified(true)
//					.enabled(true)
//					.password(encoder.encode("admin1234"))
//					.build();
//			admin.setRole(adminRole);
//			userRepository.save(admin);
//		};
//	}
