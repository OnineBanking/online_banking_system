package com.dxc.obs.api.payload.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UserRequest {
	
	private String userName;
	
	private String email;
	
	private String password;
	
	private Long phoneNumber;
	
	private Long branch;
	
	private Long accountType;
	
	private BigDecimal openingBalance;
	
	private String gender;
	

}
