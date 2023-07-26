package com.ecommerce.techzone.controller.user;

import com.ecommerce.techzone.service.FirebaseService;
import com.ecommerce.techzone.service.user.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OtpController {

    @Autowired
    FirebaseService firebaseService;
    @Autowired
    OtpService otpService;

    @GetMapping("/enterOtp")
    public String enterOtp(@RequestParam("email") String email, Model model) {
        String otp = otpService.generateOTP();
        model.addAttribute("email",email);
        otpService.sendMail(email,otp);
        return "enterOtp";
    }
    @PostMapping("/submitOtp")
    public String submitOtp(@RequestParam String otp, @RequestParam String email, Model model){
        if(otpService.verifyEmailOtp(otp,email)){
            return "/";
        } else {
            model.addAttribute("otperror",otp);
        }
        return "redirect:/";
    }
}
