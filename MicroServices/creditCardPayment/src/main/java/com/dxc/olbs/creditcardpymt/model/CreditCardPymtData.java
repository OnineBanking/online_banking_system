package com.dxc.olbs.creditcardpymt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANSACTION_INFO")
public class CreditCardPymtData {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TXNSEQ")
    @SequenceGenerator(sequenceName = "TXNSEQ", allocationSize = 1, name = "TXNSEQ")
	@Column(name = "tnumber")
	private Long txnNumber;
		
	@Column(name = "from_actnumber")
	private Long accountNumber;
	
	@Column(name = "to_actnumber")
	private String creditCardNumber;
	
	@Column(name = "cc_cvv_num")
	private int cvv;
	
	@Column(name = "cc_exp_mm")
	private int expiryMonth;
	
	@Column(name = "cc_exp_yyyy")
	private int expiryYear;
	
	@Column(name = "txn_amount")
	private Double amount;
	
	@Column(name = "MEDIUM_OF_TXN")
	private String txnMedium;
	
	@Column(name = "TXN_TYPE")
	private String txnType;
	
	@Column(name = "TXN_DESCRIPTION")
	private String txnDescription;
	
	public String getTxnDescription() {
		return txnDescription;
	}
	public void setTxnDescription(String txnDescription) {
		this.txnDescription = txnDescription;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnMedium() {
		return txnMedium;
	}
	public void setTxnMedium(String txnMedium) {
		this.txnMedium = txnMedium;
	}
	public Long getTxnNumber() {
		return txnNumber;
	}
	public void setTxnNumber(Long txnNumber) {
		this.txnNumber = txnNumber;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public int getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public int getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
