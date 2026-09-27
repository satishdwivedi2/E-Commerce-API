package com.example.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter 
public class SignupRequestDto {
    @NotBlank 
    private String name;
    @NotBlank
    @Email 
    @Pattern(regexp = ".*@gmail\\.com") 
    private String email;
    @NotBlank 
    @Pattern(regexp = "\\d{10}")
    private String phoneNo;
    @Size (min=8)
    @NotBlank 
    @Pattern(regexp = "^(?=.*\\d)(?=.*[^A-Za-z0-9]).*$")
    private String password;

}
