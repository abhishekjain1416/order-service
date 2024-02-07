package com.programmingtechie.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ParentResponse {

	@JsonProperty("mc")
	private String messageCode;
	
	@JsonProperty("m")
	private String message;
}
