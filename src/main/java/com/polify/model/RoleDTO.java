package com.polify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class RoleDTO {

    @Getter
    @JsonProperty
    private List<Map<String, Object>> roleList;
}
