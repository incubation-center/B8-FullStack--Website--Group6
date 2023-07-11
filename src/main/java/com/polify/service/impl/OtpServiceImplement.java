package com.polify.service.impl;

import com.polify.entity.OTP;
import com.polify.repository.OtpRepository;
import com.polify.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class OtpServiceImplement implements OtpService {
    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserDetailServiceImpl userDetailService;


    @Override
    public OTP addOtp(OTP otp) {
        return otpRepository.save(otp);
    }

    @Override
    public Optional<OTP> findCodeByCreatedBy(String created_by) {
        return otpRepository.findCodeByCreatedBy(created_by);
    }
    @Override
    public Optional<OTP> findCodeByCodeAndCreatedBy(int code, String created_by) {
        return otpRepository.findCodeByCodeAndCreatedBy(code, created_by);
    }

}