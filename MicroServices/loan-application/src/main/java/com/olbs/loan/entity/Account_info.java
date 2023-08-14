package com.olbs.loan.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ACCOUNT_INFO")
public class Account_info {

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
	
	@ManyToOne
	private Customer_Info cust;

	public Long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(Long accNumber) {
		this.accNumber = accNumber;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Date getAod() {
		return aod;
	}

	public void setAod(Date aod) {
		this.aod = aod;
	}

	public AccountType getAcctype() {
		return acctype;
	}

	public void setAcctype(AccountType acctype) {
		this.acctype = acctype;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Customer_Info getCust() {
		return cust;
	}

	public void setCust(Customer_Info cust) {
		this.cust = cust;
	}

	public Account_info(Long accNumber, UserDetails user, Branch branch, BigDecimal openingBalance, Date aod,
			AccountType acctype, String accStatus, Date updatedDate, Customer_Info cust) {
		super();
		this.accNumber = accNumber;
		this.user = user;
		this.branch = branch;
		this.openingBalance = openingBalance;
		this.aod = aod;
		this.acctype = acctype;
		this.accStatus = accStatus;
		this.updatedDate = updatedDate;
		this.cust = cust;
	}

	public Account_info() {
			}
	
	
	

}
