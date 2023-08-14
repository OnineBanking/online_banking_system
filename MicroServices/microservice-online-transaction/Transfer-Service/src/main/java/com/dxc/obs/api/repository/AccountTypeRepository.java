package com.dxc.obs.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.entity.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long>{

	Optional<AccountType> findByAccTypeId(Long acctypeId);

	List<AccountType> findAllByOrderByAccTypeIdAsc();


}
