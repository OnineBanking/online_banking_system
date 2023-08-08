package com.dxc.obs.api.payload.request;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class UserRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private Long phoneNumber;
	
	private Long branch;
	
	private Long accountType;
	
	private BigDecimal openingBalance;
	
	private String gender;
	
	private String city;
	
	private Date dob;
	
	private String occupation;
	
	
	

}
