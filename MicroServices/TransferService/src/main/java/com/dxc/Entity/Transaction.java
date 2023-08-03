package com.dxc.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderAccount;
    private String receiverAccount;
    private double amount;
    private LocalDateTime timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}
	public String getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

    
}
