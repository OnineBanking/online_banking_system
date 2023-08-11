package com.dxc.obs.api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dxc.obs.api.entity.Account.AccountBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
