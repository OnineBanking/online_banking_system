package com.olbs.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olbs.loan.entity.Loan;

@Repository
public interface LoanRepositary extends JpaRepository<Loan, Integer> {
	
	List<Loan> findByCustCustid(int custid);
	List<Loan> getLoansByCustCustid(int custid);
	Loan getLoanBylnumber(int lnumber);
	//Loan getAccountByacnumber(int acnumber);
	//List<Loan> updatestatus(int lnumber);

}
