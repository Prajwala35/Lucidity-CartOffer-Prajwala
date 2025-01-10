package com.springboot.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    public ApiResponse() {
		// TODO Auto-generated constructor stub
	}

	private String response_msg;

}
