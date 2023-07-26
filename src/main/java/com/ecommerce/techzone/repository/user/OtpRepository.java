package com.ecommerce.techzone.repository.user;

import com.ecommerce.techzone.entity.user.OTP;
import com.ecommerce.techzone.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OtpRepository extends JpaRepository<OTP, UUID> {
    OTP findByUser(User user);
}
