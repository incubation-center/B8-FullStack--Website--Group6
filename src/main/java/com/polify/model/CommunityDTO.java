package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class CommunityDTO {

    @Getter
    @JsonProperty
    private String communityName;

    @Getter
    @JsonProperty
    private String communityDescription;
}
