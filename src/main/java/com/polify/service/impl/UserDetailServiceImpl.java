package com.polify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.polify.entity.User;
import com.polify.repository.UserRepository;
import com.polify.service.UserAccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserAccountService {
    
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userDetails = userRepository.findByUsername(username);
		if (!userDetails.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(userDetails.get().getUsername(),
				userDetails.get().getPassword(), new ArrayList<>());
	}

	@Override
	public User save(User user) throws IllegalArgumentException {
		return userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

    @Override
    public User getByEmail(String email){return userRepository.findUserByEmail(email);}

    @Override
    public List<User> getAllUser(Long id) {
        List<User> result = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        for (User user: allUsers){
            if (user.getId() != id){
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
