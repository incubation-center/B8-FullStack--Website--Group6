package com.polify.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polify.entity.LoginHistory;

@Service
public interface LoginHistoryService {
	
	public LoginHistory save(String email, String attemptStatus);
	
	public LoginHistory save(LoginHistory entity);
	
	public List<LoginHistory> findByEmail(String email);

}
