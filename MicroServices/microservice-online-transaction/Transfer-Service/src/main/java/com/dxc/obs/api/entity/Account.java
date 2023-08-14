package com.dxc.obs.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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
