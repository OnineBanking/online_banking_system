package com.dxc.obs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccountAccNumber(Long accNo);

}
