package com.dxc.obs.api.service;

import java.util.Optional;

import com.dxc.obs.api.entity.Account;

public interface AccountService {


	Optional<Account> getAccInfoById(Long userId);

}
