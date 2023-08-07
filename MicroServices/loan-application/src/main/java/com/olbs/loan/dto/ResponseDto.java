package com.olbs.loan.dto;

import lombok.Builder;

@Builder
public class ResponseDto {

	private String message;
	private Object obj;
	
	public ResponseDto() {
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public ResponseDto(String message, Object obj) {
		super();
		this.message = message;
		this.obj = obj;
	}
	
	
	
}
