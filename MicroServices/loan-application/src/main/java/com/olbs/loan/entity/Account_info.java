package com.olbs.loan.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="ACCOUNT_INFO")
public class Account_info {
	public Account_info() {
	
		// TODO Auto-generated constructor stub
	}

	@Id
	private int acnumber; 
	private int custid;  
	private int bid; 
	private int opening_balance;
	private Date aod;
	private String atypeid; 
	private String astatus;
	
	public int getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(int acnumber) {
		this.acnumber = acnumber;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getOpening_balance() {
		return opening_balance;
	}
	public void setOpening_balance(int opening_balance) {
		this.opening_balance = opening_balance;
	}
	public Date getAod() {
		return aod;
	}
	public void setAod(Date aod) {
		this.aod = aod;
	}
	public String getAtypeid() {
		return atypeid;
	}
	public void setAtypeid(String atypeid) {
		this.atypeid = atypeid;
	}
	public String getAstatus() {
		return astatus;
	}
	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}
	
	public Account_info(int acnumber, int custid, int bid, int opening_balance, Date aod, String atypeid,
			String astatus) {
		super();
		this.acnumber = acnumber;
		this.custid = custid;
		this.bid = bid;
		this.opening_balance = opening_balance;
		this.aod = aod;
		this.atypeid = atypeid;
		this.astatus = astatus;
	}
	
	

}
