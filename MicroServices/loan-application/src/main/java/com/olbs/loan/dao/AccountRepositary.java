package com.olbs.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olbs.loan.entity.Account_info;
import com.olbs.loan.entity.Loan;
@Repository
public interface AccountRepositary extends JpaRepository<Account_info, Long> {

	Account_info findByAccNumber(Long acnumber);
  
	
}
