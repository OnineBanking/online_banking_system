package com.dxc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.Entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
