package com.olbs.loan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olbs.loan.entity.Loan;

@Repository
public interface LoanRepositary extends JpaRepository<Loan, Long> {
	
	List<Loan> findByCustCustId(Long custid);
	List<Loan> getLoansByCustCustId(Long custId);
	Loan getLoanByLoanId(Long lnumber);

}
