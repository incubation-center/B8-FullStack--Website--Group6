package com.polify.repository;

import com.polify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.polify.entity.OTP;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OTP,Integer> {
    Optional<OTP> findCodeByCreatedBy(String created_by);

    Optional<OTP> findCodeByCodeAndCreatedBy(int code, String created_by);
}
