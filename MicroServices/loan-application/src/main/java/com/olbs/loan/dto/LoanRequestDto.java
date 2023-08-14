package com.olbs.loan.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 */
@Data
public class LoanRequestDto {

	private Long lnumber;
	private Long bid;
	private BigDecimal loan_amount;
	private double ltenure;
	private BigDecimal lemi;
	private Long custid;
	private String status;
	private Long acnumber;
	private String intrest;
	
	public Long getLnumber() {
		return lnumber;
	}
	public void setLnumber(Long lnumber) {
		this.lnumber = lnumber;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public BigDecimal getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(BigDecimal loan_amount) {
		this.loan_amount = loan_amount;
	}
	public double getLtenure() {
		return ltenure;
	}
	public void setLtenure(double ltenure) {
		this.ltenure = ltenure;
	}
	public BigDecimal getLemi() {
		return lemi;
	}
	public void setLemi(BigDecimal lemi) {
		this.lemi = lemi;
	}
	public Long getCustid() {
		return custid;
	}
	public void setCustid(Long custid) {
		this.custid = custid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(Long acnumber) {
		this.acnumber = acnumber;
	}
	public String getIntrest() {
		return intrest;
	}
	public void setIntrest(String intrest) {
		this.intrest = intrest;
	}
	public LoanRequestDto(Long lnumber, Long bid, BigDecimal loan_amount, double ltenure, BigDecimal lemi, Long custid,
			String status, Long acnumber, String intrest) {
		super();
		this.lnumber = lnumber;
		this.bid = bid;
		this.loan_amount = loan_amount;
		this.ltenure = ltenure;
		this.lemi = lemi;
		this.custid = custid;
		this.status = status;
		this.acnumber = acnumber;
		this.intrest = intrest;
	}
	public LoanRequestDto() {

	}
	
	
	

}
