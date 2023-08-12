package com.olbs.loan.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.olbs.loan.entity.Customer_Info;

public interface CustomerRepository extends JpaRepository<Customer_Info, Long>{


	Customer_Info findByPhoneNumber(Long phoneNumber);

	Customer_Info findByCustId(Long custid);



		

}
