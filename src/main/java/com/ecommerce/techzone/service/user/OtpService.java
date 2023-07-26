package com.ecommerce.techzone.service.user;

import com.ecommerce.techzone.entity.user.OTP;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.user.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    UserService userService;

    @Autowired
    private EmailSenderService senderService;

    public void save(OTP otp) {
        otpRepository.save(otp);
    }


    public OTP findByUser(User user) {
        return otpRepository.findByUser(user);
    }

    // Method to generate a 6-digit numeric OTP
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void sendMail(String email, String otp){
//        String toEmail=user.getEmail();
        senderService.sendMail(email,
                "TechZone - OTP",
                "Your OTP is: "+ otp);
    }

    public boolean verifyEmailOtp(String otp, String email) {
        User user = userService.findByEmail(email);
        if(user.isVerified()){
            return true;
        }
        OTP savedOtp = this.findByUser(user);
            if(otp.equals(savedOtp.getOtp())){
                user.setVerified(true);
                return true;
            }

        return false;
    }
}
