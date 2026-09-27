package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Optional<Otp> findByPhoneNo(String phoneNo);
    void deleteByPhoneNo(String phoneNo);

}
