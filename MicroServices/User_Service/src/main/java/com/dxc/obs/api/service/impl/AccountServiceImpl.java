package com.dxc.obs.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.obs.api.entity.Account;
import com.dxc.obs.api.exception.BadRequestException;
import com.dxc.obs.api.repository.AccountRepository;
import com.dxc.obs.api.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository AccountRepo;

	@Override
	public Optional<Account> getAccInfoById(Long userId) {
		log.info("getAccInfoById service - start");
		Optional<Account> optionalUser = AccountRepo.findByUserUserId(userId);

		if (optionalUser.isEmpty()) {
			log.debug("AccountDetails Not Found with user id {}", userId);
			throw new BadRequestException("AccountDetails Not Found with user id " + userId);
		} else {
			return optionalUser;
		}
	}

}
