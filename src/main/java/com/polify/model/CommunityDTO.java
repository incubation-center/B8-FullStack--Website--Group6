package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public class CommunityDTO {

    @Getter
    @JsonProperty
    private String communityName;

    @Getter
    @JsonProperty
    private String image;

    @Getter
    @JsonProperty
    private String communityDescription;

    @Getter
    @JsonProperty
    private List<Long> userId;
}
