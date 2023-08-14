package com.dxc.obs.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccNumber(Long fromAccountNo);

	List<Account> findByAcctypeAccTypeId(Long accTypeId);

	Optional<Account> findByUserUserIdAndAcctypeAccTypeId(Long userId, Long acctypeId);

	List<Account> findByUserUserId(Long userId);

}
