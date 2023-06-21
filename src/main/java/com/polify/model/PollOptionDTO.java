package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class PollOptionDTO {

    @Getter
    @JsonProperty
    private String optionText;

    @Getter
    @JsonProperty
    private Long poll_id;
}
