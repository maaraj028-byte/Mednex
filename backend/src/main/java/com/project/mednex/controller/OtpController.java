package com.project.mednex.controller;

import com.project.mednex.service.OtpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OtpServices otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");

        return otpService.sendOtp(email);
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String otp = request.get("otp");

        boolean isValid = otpService.verifyOtp(email, otp);

        if (isValid) {
            return "OTP verified successfully";
        } else {
            return "Invalid OTP";
        }
    }
}