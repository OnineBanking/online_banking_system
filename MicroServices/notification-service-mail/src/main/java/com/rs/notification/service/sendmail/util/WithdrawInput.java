package com.rs.notification.service.sendmail.util;


import java.util.Objects;

public class WithdrawInput extends AccountInput{
	 String accountNumber;

//    @Positive(message = "Transfer amount must be positive")
    private double amount;

    public WithdrawInput() {
        this.accountNumber = super.getAccountNumber();
    }

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

 

   
}