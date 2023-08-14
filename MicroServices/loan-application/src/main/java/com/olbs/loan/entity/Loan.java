package com.olbs.loan.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOAN")
public class Loan {

    @Id
 	private Long loanId;
    
    @ManyToOne
    private Branch bid; 
    
    private BigDecimal loan_amount;
    
    private double loanTenure;
    
    private BigDecimal loanEmi; 
    
    private String status;
    
    private String interest;
    
    @ManyToOne
    private Customer_Info cust;

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Branch getBid() {
		return bid;
	}

	public void setBid(Branch bid) {
		this.bid = bid;
	}

	public BigDecimal getLoan_amount() {
		return loan_amount;
	}

	public void setLoan_amount(BigDecimal loan_amount) {
		this.loan_amount = loan_amount;
	}

	public double getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(double loanTenure) {
		this.loanTenure = loanTenure;
	}

	public BigDecimal getLoanEmi() {
		return loanEmi;
	}

	public void setLoanEmi(BigDecimal loanEmi) {
		this.loanEmi = loanEmi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public Customer_Info getCust() {
		return cust;
	}

	public void setCust(Customer_Info cust) {
		this.cust = cust;
	}

	public Loan(Long loanId, Branch bid, BigDecimal loan_amount, double loanTenure, BigDecimal loanEmi, String status,
			String interest, Customer_Info cust) {
		super();
		this.loanId = loanId;
		this.bid = bid;
		this.loan_amount = loan_amount;
		this.loanTenure = loanTenure;
		this.loanEmi = loanEmi;
		this.status = status;
		this.interest = interest;
		this.cust = cust;
	}

	public Loan() {

	}
    
    
    
		
}
