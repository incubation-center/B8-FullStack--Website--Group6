package com.polify.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.polify.entity.User;

import java.util.List;

@Service
public interface UserAccountService extends UserDetailsService {
	
	public User save(User user);

	public User getUserByUsername(String username);

    public List<User> getAllUser(Long id);

    public User getUserById(Long id);

    public User getByEmail(String email);

}
