package com.olbs.loan.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.olbs.loan.entity.Transaction_Info;

public interface TransactionRepositary extends JpaRepository<Transaction_Info, Integer> {

}
