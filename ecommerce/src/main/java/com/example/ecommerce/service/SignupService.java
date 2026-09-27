package com.example.ecommerce.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.dto.SignupRequestDto;
import com.example.ecommerce.entity.Otp;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.OtpRepository;
import com.example.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SignupService {
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
   
    public void signUp(SignupRequestDto signupRequestDto){
        String d=signupRequestDto.getEmail();
        String e=signupRequestDto.getPhoneNo();
        if( userRepository.existsByEmail(d) || userRepository.existsByPhoneNo(e)){
            System.out.println("User already exists");
        }       
         else{
         User user=new User();
         user.setName(signupRequestDto.getName());
         user.setPhoneNo(signupRequestDto.getPhoneNo());
         user.setEmail(signupRequestDto.getEmail());
         user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
         user.setPhoneVerified(false);
         user.setRole(Role.USER);
            userRepository.save(user);
            Otp otp=new Otp();
            otp.setPhoneNo(signupRequestDto.getPhoneNo());
            otp.setOtp(String.valueOf((int)(Math.random()*9000)+1000));
            otp.setExpiryTime(LocalDateTime.now().plusMinutes(2));
            otpRepository.save(otp);
            System.out.println(otp.getOtp());
    }
}    @Transactional 
    public boolean verifyOtp(String phoneNo, String otp) {
        Otp otpEntity = otpRepository.findByPhoneNo(phoneNo)
                .orElseThrow(() -> new IllegalArgumentException
                ("OTP not found for the provided phone number"));

        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("OTP has expired");
        }

        if (!otpEntity.getOtp().equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP");
        }
        User user = userRepository.findByPhoneNo(phoneNo)
                .orElseThrow(() -> new IllegalArgumentException
                ("User not found for the provided phone number"));
        user.setPhoneVerified(true);
        userRepository.save(user);
        otpRepository.deleteByPhoneNo(phoneNo);
        return true;

}
    
}