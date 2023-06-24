package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class VoteDTO {

    @Getter
    @JsonProperty
    private List<Long> option_id;
}
