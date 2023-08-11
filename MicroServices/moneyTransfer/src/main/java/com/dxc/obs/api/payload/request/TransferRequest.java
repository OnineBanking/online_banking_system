package com.dxc.obs.api.payload.request;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TransferRequest {
	
	private Long userId;
	
	private Long fromAccountNo;
	
    private Date dot;
	
	private String mediumOfTrans;
	
	private String transType;
	
	private BigDecimal transAmount;
	
	private String toAccountNo;
	
	private String comments;
	

}
