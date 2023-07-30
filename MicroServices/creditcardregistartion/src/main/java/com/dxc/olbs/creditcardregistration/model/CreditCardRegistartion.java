package com.dxc.olbs.creditcardregistration.model;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	private String bid;
	
	@Column(name = "CC_AMOUNT_LIMIT")
	private String ccAmountLimit;
	
	@Column(name = "BILLING_SC")
	private String billingSc;
	
	@JsonProperty("custData")
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name = "CUSTID", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private CustomerData custData;
	
	public CustomerData getCustData() {
		return custData;
	}
	public void setCustData(CustomerData custData) {
		this.custData = custData;
	}
	public Long getCcnumber() {
		return ccnumber;
	}
	public void setCcnumber(Long ccnumber) {
		this.ccnumber = ccnumber;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getCcAmountLimit() {
		return ccAmountLimit;
	}
	public void setCcAmountLimit(String ccAmountLimit) {
		this.ccAmountLimit = ccAmountLimit;
	}
	public String getBillingSc() {
		return billingSc;
	}
	public void setBillingSc(String billingSc) {
		this.billingSc = billingSc;
	}
	public String toString() {
		return"ccnumber::"+ccnumber+", custid::" + ", bid::"+bid +", cc_amount_limit ::"+ccAmountLimit +", billing_sc::"+billingSc;
	}
	

}
