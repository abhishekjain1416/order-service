package com.programmingtechie.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter
public class ErrorResponse {

    @JsonProperty("di")
    @Schema(description = "di : request debug id")
    private String debugId;

    @JsonProperty("em")
    @Schema(description = "em : error message")
    private String errorMessage;
}
