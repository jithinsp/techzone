package com.ecommerce.techzone.controller.user;

import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.service.FirebaseService;
import com.ecommerce.techzone.service.user.OtpService;
import com.ecommerce.techzone.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    FirebaseService firebaseService;
    @Autowired
    OtpService otpService;

    @Autowired
    UserService userService;

    @GetMapping("/enterOtp")
    public String enterOtp(@AuthenticationPrincipal(expression = "username") String username, Model model) {
        String otp = otpService.generateOTP();
//        String email = user.getEmail();
        String email = "jithinsp001@gmail.com";
        userService.saveOTP(otp,username);
        System.out.println(username + "  OTP:  "+otp);
        model.addAttribute("username",username);
//        otpService.sendMail(email,otp);
        return "user/enterOtp";
    }
    @PostMapping("/submitOtp")
    public String submitOtp(@RequestParam String otp, @AuthenticationPrincipal(expression = "username") String username, Model model){

        if(!otpService.verifyEmailOtp(otp,username)){
            model.addAttribute("otperror",otp);
            System.out.println("error in otp");
            return "user/enterOtp";
        }
        return "redirect:/";
    }
}
