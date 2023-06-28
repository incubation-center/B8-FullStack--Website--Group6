package com.polify.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor()
@AllArgsConstructor
public class UserDTO {
	
	@JsonProperty("id")
    private long id;
	
	@NotNull(message = "Please provide username")
	@JsonProperty("username")
    private String username;
	
	@JsonProperty("password")
    private String password;

	@JsonProperty("email")
    private String email;
	
	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

}
