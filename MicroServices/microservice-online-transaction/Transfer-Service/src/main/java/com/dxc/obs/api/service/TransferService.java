package com.dxc.obs.api.service;

import java.util.List;

import com.dxc.obs.api.entity.Account;
import com.dxc.obs.api.entity.AccountType;
import com.dxc.obs.api.entity.Branch;
import com.dxc.obs.api.entity.Transaction;
import com.dxc.obs.api.payload.request.AccountRequest;
import com.dxc.obs.api.payload.request.TransferRequest;

public interface TransferService {

	void createAccType(String accType);

	void createBranch(String branchName, String city);

	String createAccount(AccountRequest accountRequest);

	List<Account> findByAccInfoById(Long userId);

	String moneyTransfer(TransferRequest transferRequest);

	List<AccountType> getAccTypes();

	List<Branch> getAllBranches();

	List<Account> getAccNosByAccTypeId(Long accTypeId);

	List<Transaction> getTransHistoryById(Long accNo);


}
