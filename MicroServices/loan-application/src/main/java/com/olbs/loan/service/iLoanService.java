package com.olbs.loan.service;

import java.util.List;

import com.olbs.loan.dto.LoanRequestDto;
import com.olbs.loan.entity.Account_info;
import com.olbs.loan.entity.Customer_Info;
import com.olbs.loan.entity.Loan;

public interface iLoanService {
	public Loan applyLoan(LoanRequestDto l);
	public List<Loan> getLoansByCustId(int custId);
	public Loan closeLoan(int loanId);
	public Customer_Info addCustomner(Customer_Info c);
	public Account_info emiPayment(LoanRequestDto loan);
}
