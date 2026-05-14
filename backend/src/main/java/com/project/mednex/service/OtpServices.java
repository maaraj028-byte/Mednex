package com.project.mednex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class OtpServices {

    @Autowired
    private JavaMailSender mailSender;

    private HashMap<String, String> otpStorage = new HashMap<>();

    public String sendOtp(String email) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        otpStorage.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("MedNex OTP Verification");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        return "OTP sent successfully";
    }

    public boolean verifyOtp(String email, String otp) {

        String storedOtp = otpStorage.get(email);

        return storedOtp != null && storedOtp.equals(otp);
    }
}