package com.dxc.obs.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{

	Optional<Branch> findByBranchId(Long branch);


}
