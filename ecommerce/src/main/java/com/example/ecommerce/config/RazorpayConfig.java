package com.example.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;


@Configuration 
public class RazorpayConfig {
    
    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @Bean
    public RazorpayClient razorpayClient() throws Exception {
        System.out.println("Key ID present: " + (keyId != null && !keyId.isBlank()));
System.out.println("Secret present: " + (keySecret != null && !keySecret.isBlank()));
        return new RazorpayClient(keyId, keySecret);
    }
}
