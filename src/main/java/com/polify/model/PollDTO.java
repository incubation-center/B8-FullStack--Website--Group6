package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class PollDTO {

    @Getter
    @JsonProperty
    private String pollQuestion;

    @Getter
    @JsonProperty
    private String pollDescription;

    @Getter
    @JsonProperty
    private Long post_id;
}
