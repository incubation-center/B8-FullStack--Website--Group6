package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class VoteDTO {

    @Getter
    @JsonProperty
    private Long option_id;

    @Getter
    @JsonProperty
    private Long user_id;
}
