package com.polify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor()
@AllArgsConstructor
public class VerifyUserDTO {
	
	@JsonProperty("id")
    private long id;
	
	@NotNull(message = "Please provide username")
	@JsonProperty("username")
    private String username;

	@JsonProperty("code")
    private int code;

}
