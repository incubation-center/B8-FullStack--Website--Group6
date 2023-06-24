package com.polify.utils;

import com.polify.entity.LoginHistory;
import com.polify.model.LoginHistoryDTO;

public class LoginHistoryConverter extends Converter<LoginHistoryDTO, LoginHistory> {
	
	public LoginHistoryConverter() {
		super(LoginHistoryConverter::convertToEntity, LoginHistoryConverter::convertToDto);
	}
	
	private static LoginHistoryDTO convertToDto(LoginHistory loginHistory) {		
		return new LoginHistoryDTO(loginHistory.getId(), loginHistory.getEmail(), loginHistory.getAttemptStatus(),
				loginHistory.getAttemptAt());
	}

	private static LoginHistory convertToEntity(LoginHistoryDTO loginHistoryDTO) {
		return new LoginHistory(loginHistoryDTO.getId(), loginHistoryDTO.getEmail(), loginHistoryDTO.getAttemptStatus(),
				loginHistoryDTO.getAttemptAt());
	}
	

}
