package com.olbs.loan.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction_Info {
	@Id
	private Long transId;
	
	@ManyToOne
	private Account_info account;
	
	@ManyToOne
	private UserDetails user;
	
	private Date dot;
	
	private String mediumOfTrans;
	
	private String transType;
	
	private BigDecimal transAmount;
	
	private String toAccountNo;
	
	private String comments;
	
	private BigDecimal balance;
	

}
