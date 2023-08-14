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
@Table(name ="TRANSACTION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	@Id
	private Long transId;
	
	@ManyToOne
	private Account account;
	
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
