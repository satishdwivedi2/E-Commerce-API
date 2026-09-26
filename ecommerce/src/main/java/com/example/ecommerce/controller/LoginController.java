package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.dto.LoginResponseDto;
import com.example.ecommerce.service.LoginService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/login")
@RestController 
@RequiredArgsConstructor 
public class LoginController {
  private final LoginService loginService;
  @PostMapping
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return loginService.login(loginRequestDto);
    }
    @GetMapping ("/test")
    public String test() {
        return "Login test";
    }

}
