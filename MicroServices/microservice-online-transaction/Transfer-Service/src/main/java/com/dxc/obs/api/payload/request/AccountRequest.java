package com.dxc.obs.api.payload.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountRequest {
	
	private Long userId;
	
	private Long branchId;
	
	private BigDecimal openingBalance;
	
	private Long acctypeId;
	
	private String accStatus;

}
