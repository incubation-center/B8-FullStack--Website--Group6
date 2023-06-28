package com.polify.utils;

import com.polify.entity.User;
import com.polify.model.UserDTO;


public class UserConverter extends Converter<UserDTO, User> {

	public UserConverter() {
		super(UserConverter::convertToEntity, UserConverter::convertToDto);
	}

	private static UserDTO convertToDto(User user) {		
		return new UserDTO(ProjectUtils.getValue(user.getId()), ProjectUtils.getValue(user.getUsername()), null,
				user.getEmail());
	}

	private static User convertToEntity(UserDTO user) {
		return new User(ProjectUtils.getValue(user.getId()), user.getUsername(), ProjectUtils.getValue(user.getPassword()),
				user.getEmail());
	}
		
}	
