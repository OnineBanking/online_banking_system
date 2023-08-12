package com.olbs.loan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olbs.loan.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{

	Branch findByBranchId(Long bid);

}
