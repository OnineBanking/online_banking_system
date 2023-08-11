package com.dxc.obs.api.payload.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanRequest {
	
	
	private String bid;
	
	private BigDecimal loanAmount;
	
	private String loanTenure;
	
	private BigDecimal loanEmi;
	
	private Long custId;

}
