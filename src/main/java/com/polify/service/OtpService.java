package com.polify.service;

import com.polify.entity.OTP;

import java.util.Optional;

public interface OtpService {
    public  OTP addOtp(OTP otp);
    public Optional<OTP> findCodeByCreatedBy(String created_by);
    public Optional<OTP> findCodeByCodeAndCreatedBy(int code, String created_by);

}