package com.dxc.obs.api.payload.request;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerRequest {
	
private String firstName;
	
	
	private String lastName;
	
	private String city;
	
	private Long mobileNumber;
	
	private String email;
	
	private Date dob;
	
	private String occupation;

}
