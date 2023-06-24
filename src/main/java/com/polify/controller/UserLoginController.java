package com.polify.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polify.entity.User;
import com.polify.exception.ResourceNotFoundException;
import com.polify.model.LoginHistoryDTO;
import com.polify.model.UserDTO;
import com.polify.service.LoginHistoryService;
import com.polify.service.UserAccountService;
import com.polify.utils.LoginHistoryConverter;
import com.polify.utils.ProjectUtils;
import com.polify.utils.UserConverter;

/**
 * The type User controller.
 *
 */
@RestController
@RequestMapping(ProjectUtils.API_URL)
public class UserLoginController {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private LoginHistoryService loginHistoryService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private LoginHistoryConverter loginHistoryConverter;

	/**
	 * Register user.
	 *
	 * @param userDTO
	 * @return the user
	 */
	@PostMapping(ProjectUtils.REGISTER_USER_URL)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		User user = userAccountService.save(userConverter.convertFromDto(userDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(userConverter.convertFromEntity(user));
	}

	/**
	 * Get login history by username
	 *
	 * @param email
	 * @return login history
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/login-history/{email}")
	public ResponseEntity<List<LoginHistoryDTO>> getLoginHistoryByUser(
			@PathVariable(value = "email") String email) throws ResourceNotFoundException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(loginHistoryConverter.createFromEntities(loginHistoryService.findByEmail(email)));
	}

	@Bean
	public LoginHistoryConverter loginHistoryConverter() {
		return new LoginHistoryConverter();
	}

	@Bean
	public UserConverter userConverter() {
		return new UserConverter();
	}
}
