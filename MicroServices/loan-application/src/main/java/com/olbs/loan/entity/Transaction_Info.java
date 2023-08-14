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

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Account_info getAccount() {
		return account;
	}

	public void setAccount(Account_info account) {
		this.account = account;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Date getDot() {
		return dot;
	}

	public void setDot(Date dot) {
		this.dot = dot;
	}

	public String getMediumOfTrans() {
		return mediumOfTrans;
	}

	public void setMediumOfTrans(String mediumOfTrans) {
		this.mediumOfTrans = mediumOfTrans;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public String getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(String toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Transaction_Info(Long transId, Account_info account, UserDetails user, Date dot, String mediumOfTrans,
			String transType, BigDecimal transAmount, String toAccountNo, String comments, BigDecimal balance) {
		super();
		this.transId = transId;
		this.account = account;
		this.user = user;
		this.dot = dot;
		this.mediumOfTrans = mediumOfTrans;
		this.transType = transType;
		this.transAmount = transAmount;
		this.toAccountNo = toAccountNo;
		this.comments = comments;
		this.balance = balance;
	}

	public Transaction_Info() {

	}
	
	

}
