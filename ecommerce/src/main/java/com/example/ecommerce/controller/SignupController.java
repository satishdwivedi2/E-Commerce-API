package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.SignupRequestDto;
import com.example.ecommerce.service.SignupService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        signupService.signUp(signupRequestDto);
        return ResponseEntity.ok("User registered successfully. Please verify your phone number.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNo, @RequestParam String otp) {
        boolean isVerified = signupService.verifyOtp(phoneNo, otp);
        if (isVerified) {
            return ResponseEntity.ok("Phone number verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP verification failed.");
        }
    }

}
