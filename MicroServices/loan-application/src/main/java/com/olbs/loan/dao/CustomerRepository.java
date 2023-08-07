package com.olbs.loan.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olbs.loan.entity.Customer_Info;

public interface CustomerRepository extends JpaRepository<Customer_Info, Integer>{
		
	Customer_Info findByMobileno(double mobileno);

	Customer_Info findByCustid(int custid);

}
