package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor()
@AllArgsConstructor
public class CommunityMembersDTO {

    @NotNull
    @JsonProperty
    private Long user_id;

    @NotNull
    @JsonProperty
    private Long community_id;
}
