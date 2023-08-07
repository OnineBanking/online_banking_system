package com.olbs.loan.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TRANSACTION_INFO")
public class Transaction_Info {
	@Id
	private long tnumber;
	private int acnumber;
	private Date dot;
	private String medium_of_transaction;
	private String transaction_type;
	private double transaction_amount;
	
	public long getTnumber() {
		return tnumber;
	}
	public void setTnumber(long tnumber) {
		this.tnumber = tnumber;
	}
	public int getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(int acnumber) {
		this.acnumber = acnumber;
	}
	public Date getDot() {
		return dot;
	}
	public void setDot(Date dot) {
		this.dot = dot;
	}
	public String getMedium_of_transaction() {
		return medium_of_transaction;
	}
	public void setMedium_of_transaction(String medium_of_transaction) {
		this.medium_of_transaction = medium_of_transaction;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(double d) {
		this.transaction_amount = d;
	}
	
	

}
