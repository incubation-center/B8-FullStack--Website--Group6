package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class PollDTO {

    @Getter
    @JsonProperty
    private String pollQuestion;

    @Getter
    @JsonProperty
    private int limitVote;

    @Getter
    @JsonProperty
    private Date duration;

    @Getter
    @JsonProperty
    private List<String> options;
}
