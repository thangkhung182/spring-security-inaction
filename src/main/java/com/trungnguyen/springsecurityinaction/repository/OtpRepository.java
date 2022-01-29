package com.trungnguyen.springsecurityinaction.repository;

import com.trungnguyen.springsecurityinaction.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findByUsername(String username);
}
