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
public class ProfileDTO {
	
	@JsonProperty("id")
    private long id;


    @JsonProperty("image")
    private String image;


}
