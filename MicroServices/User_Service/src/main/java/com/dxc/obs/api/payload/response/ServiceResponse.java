package com.dxc.obs.api.payload.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse <T>{
	
	private String responseMsg;
	
	private String data;

}
