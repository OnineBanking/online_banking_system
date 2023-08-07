package com.olbs.loan.exceptions;

public class LoanNotFoundException extends RuntimeException{
	
	public LoanNotFoundException(String msg){
		super(msg);
	}

}
