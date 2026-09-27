package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
     boolean existsByEmail(String email);
     boolean existsByPhoneNo(String phoneNo);
     
	 Optional<User> findByPhoneNo(String phoneNo);

        

}
