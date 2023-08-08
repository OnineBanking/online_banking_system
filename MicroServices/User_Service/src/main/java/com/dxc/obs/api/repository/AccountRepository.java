package com.dxc.obs.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByUserUserId(Long userId);

}
