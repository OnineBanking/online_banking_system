package com.olbs.loan.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ACCOUNT_INFO")
public class Account_info {

	@Id
	private Long accNumber;
	
	@ManyToOne
	private UserDetails user;
	
	@ManyToOne
	private Branch branch;
	
	private BigDecimal openingBalance;
		
	private Date aod;
	
	@ManyToOne
	private AccountType acctype;
	
	private String accStatus;
	
	private Date updatedDate;
	
	@ManyToOne
	private Customer_Info cust;

}
