package com.ecommerce.techzone.service.user;

import com.ecommerce.techzone.entity.user.OTP;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.user.OtpRepository;
import com.ecommerce.techzone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    UserRepository userRepository;

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
        senderService.sendMail(email,
                "TechZone - OTP",
                "Your OTP is: "+ otp);
    }

    public boolean verifyEmailOtp(String otp, String username) {
        System.out.println("submit: "+username +" otp: " + otp);
        User user = userService.findByUsername(username);
        if(user.isVerified()){
            System.out.println("user already verified");
            return true;
        }
        OTP savedOtp = this.findByUser(user);
        System.out.println(otp + "saved otp: "+savedOtp.getOtp());
            if(otp.equals(savedOtp.getOtp())){

                System.out.println("setVerified: false"+user.isVerified());
                user.setVerified(true);
                userRepository.save(user);
//                deleteotp
                System.out.println("setVerified: true"+user.isVerified());
                return true;
            }

        System.out.println("setVerified: false");
        return false;
    }
}
