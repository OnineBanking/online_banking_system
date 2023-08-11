package com.olbs.loan.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOAN")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)
 	private int lnumber;
    private String bid; 
    private int loan_amount;
    private double ltenure;
    private double lemi; 
    private String status;
    private String interest;
    
    public String getInterest() {
		return interest;
	}


	public void setInterest(String interest) {
		this.interest = interest;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public int getLnumber() {
		return lnumber;
	}

	public void setLnumber(int lnumber) {
		this.lnumber = lnumber;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public int getLoan_amount() {
		return loan_amount;
	}


	public void setLoan_amount(int loan_amount) {
		this.loan_amount = loan_amount;
	}


	public double getLtenure() {
		return ltenure;
	}


	public void setLtenure(double ltenure) {
		this.ltenure = ltenure;
	}


	public double getLemi() {
		return lemi;
	}


	public void setLemi(double lemi) {
		this.lemi = lemi;
	}
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	/* @JoinColumn(name = "custid", insertable = false, updatable = false) */
	private Customer_Info cust;
	
	public Customer_Info getCust() {
		return cust;
	}


	public void setCust(Customer_Info cust) {
		this.cust = cust;
	}
	
	
    public Loan(int lnumber, String bid, int loan_amount, double ltenure, double lemi,Customer_Info cust) {
		super();
		this.lnumber = lnumber;
		this.bid = bid;
		this.loan_amount = loan_amount;
		this.ltenure = ltenure;
		this.lemi = lemi;
		this.cust=cust;
	}


	public Loan() {
		// TODO Auto-generated constructor stub
	}
		
}
