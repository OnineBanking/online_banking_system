package com.olbs.loan.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanRequestDto {

	private Long lnumber;
	private Long bid;
	private BigDecimal loan_amount;
	private double ltenure;
	private BigDecimal lemi;
	private Long custid;
	private String status;
	private Long acnumber;
	private String intrest;

}
