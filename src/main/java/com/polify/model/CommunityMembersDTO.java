package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor()
@AllArgsConstructor
public class CommunityMembersDTO {

    @Getter
    @NotNull
    @JsonProperty
    private Long user_id;

    @Getter
    @NotNull
    @JsonProperty
    private Long community_id;
}
