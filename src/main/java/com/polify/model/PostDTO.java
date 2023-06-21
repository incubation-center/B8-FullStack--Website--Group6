package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class PostDTO {

    @Getter
    @JsonProperty
    private String postContent;

    @Getter
    @JsonProperty
    private Long user_id;

    @Getter
    @JsonProperty
    private Long community_id;
}
