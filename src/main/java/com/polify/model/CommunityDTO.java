package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor()
@AllArgsConstructor
public class CommunityDTO {

    @Getter
    @JsonProperty("communityName")
    private String communityName;

    @Getter
    @JsonProperty("communityDescription")
    private String communityDescription;

    @Getter
    @JsonProperty("userId")
    private List<Long> userId;
}
