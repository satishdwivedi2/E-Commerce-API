package com.example.ecommerce.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.dto.LoginResponseDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class LoginService {
   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtEncoder jwtEncoder;

   public LoginResponseDto login(LoginRequestDto loginRequestDto) {
       String email = loginRequestDto.getEmail();
       String password = loginRequestDto.getPassword();

       // Retrieve the user by email
       User user = userRepository.findByEmail(email)
               .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

       // Verify the password
       if (!passwordEncoder.matches(password, user.getPassword())) {
           throw new RuntimeException("Invalid password");
       }
       if (!user.isPhoneVerified()) {
            throw new RuntimeException("Phone number is not verified");
        }

        JwtClaimsSet claims = JwtClaimsSet.builder()
        .subject(user.getEmail())
        .claim("role", user.getRole().name())
        .issuedAt(Instant.now())
        .expiresAt(Instant.now().plusSeconds(3600))
        .build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
        Jwt jwt = jwtEncoder.encode(parameters);
        String token = jwt.getTokenValue();
        
    return new LoginResponseDto( token);
    }
   
}
