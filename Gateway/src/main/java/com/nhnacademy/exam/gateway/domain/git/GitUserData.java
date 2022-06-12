package com.nhnacademy.exam.gateway.domain.git;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitUserData {
    @JsonProperty("login")
    private String id;
    @JsonProperty("email")
    private String email;
}
