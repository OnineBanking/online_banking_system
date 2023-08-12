package com.dxc.obs.api.applycreditcard.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CREDIT_CARD")
public class CreditCardRegistartion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CREDITCARDAPPLICATIONSEQ")
    @SequenceGenerator(sequenceName = "CREDITCARDAPPLICATIONSEQ", allocationSize = 1, name = "CREDITCARDAPPLICATIONSEQ")
    private Long ccnumber;
	
	@Column(name = "ACCT_ID")
	Long associatedAccount;
	
	@Column(name = "CUST_ID")
	private Long custId;
	
	@Column(name = "INCOME")
	private Long income;
	
	@Column(name = "PAN")
	private String pan;
		
	@Column(name = "STATUS")
	private String status;
		
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCcnumber() {
		return ccnumber;
	}
	public void setCcnumber(Long ccnumber) {
		this.ccnumber = ccnumber;
	}
	public Long getAssociatedAccount() {
		return associatedAccount;
	}
	public void setAssociatedAccount(Long associatedAccount) {
		this.associatedAccount = associatedAccount;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
}
