package com.dxc.obs.api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name ="ACCOUNT_INFO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
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
	
	
	
	
	

}
