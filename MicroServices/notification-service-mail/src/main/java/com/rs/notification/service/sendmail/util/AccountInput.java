package com.rs.notification.service.sendmail.util;
import org.hibernate.validator.constraints.NotBlank;

public class AccountInput {

    @NotBlank(message = "Account number is mandatory")
    private String accountNumber;
    
    @NotBlank(message = "Account Name is mandatory")
    private String accountName;
    
    @NotBlank(message = "sendToAddress is mandatory")
    private String sendToAddress;
    
    @NotBlank(message = "Account number is mandatory")
    private String transactionType;

    public AccountInput() {}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSendToAddress() {
		return sendToAddress;
	}

	public void setSendToAddress(String sendToAddress) {
		this.sendToAddress = sendToAddress;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
