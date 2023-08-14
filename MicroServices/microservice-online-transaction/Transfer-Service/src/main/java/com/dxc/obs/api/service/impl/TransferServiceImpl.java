package com.dxc.obs.api.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.dxc.obs.api.event.TransferServiceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.dxc.obs.api.entity.Account;
import com.dxc.obs.api.entity.AccountType;
import com.dxc.obs.api.entity.Branch;
import com.dxc.obs.api.entity.Transaction;
import com.dxc.obs.api.entity.UserDetails;
import com.dxc.obs.api.payload.request.AccountRequest;
import com.dxc.obs.api.payload.request.TransferRequest;
import com.dxc.obs.api.repository.AccountRepository;
import com.dxc.obs.api.repository.AccountTypeRepository;
import com.dxc.obs.api.repository.BranchRepository;
import com.dxc.obs.api.repository.TransactionRepository;
import com.dxc.obs.api.repository.UserDetailsRepository;
import com.dxc.obs.api.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	AccountTypeRepository accountTypeRepo;

	@Autowired
	BranchRepository branchRepo;

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	TransactionRepository transactionRepo;

	@Autowired
	UserDetailsRepository userDetailsRepo;
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void createAccType(String accType) {
		AccountType accTypeobj = AccountType.builder().accTypeName(accType).build();
		accountTypeRepo.save(accTypeobj);

	}

	@Override
	public void createBranch(String branchName, String city) {
		Branch branch = Branch.builder().branchName(branchName).branchCity(city).build();
		branchRepo.save(branch);
	}

	@Override
	public String createAccount(AccountRequest accountRequest) {

		Optional<UserDetails> userOptional = userDetailsRepo.findById(accountRequest.getUserId());
		if (userOptional.isEmpty()) {
			return "User Id not available!";
		}
		Optional<Branch> branchOptional = branchRepo.findByBranchId(accountRequest.getBranchId());
		if (branchOptional.isEmpty()) {
			return "Branch Id not available!";
		}
		Optional<AccountType> accTypeOptional = accountTypeRepo.findByAccTypeId(accountRequest.getAcctypeId());
		if (accTypeOptional.isEmpty()) {
			return "Account Type Id not available!";
		}
		Optional<Account> optionalAcc = accountRepo.findByUserUserIdAndAcctypeAccTypeId(accountRequest.getUserId(),
				accountRequest.getAcctypeId());
		if (!optionalAcc.isEmpty()) {
			return "User alredy having " + accTypeOptional.get().getAccTypeName();
		}
		Account account = Account.builder().accNumber(genarateNumber()).acctype(accTypeOptional.get())
				.accStatus(accountRequest.getAccStatus()).branch(branchOptional.get()).user(userOptional.get())
				.openingBalance(accountRequest.getOpeningBalance()).aod(new Date()).build();

		return accountRepo.save(account).getAccNumber().toString();
	}

	private Long genarateNumber() {

		Random random = new Random();
		int randomNumber = 10000000 + random.nextInt(90000000);
		return Long.valueOf(randomNumber);

	}

	@Override
	public List<Account> findByAccInfoById(Long userId) {
		return accountRepo.findByUserUserId(userId);
	}

	@Override
	public String moneyTransfer(TransferRequest transferRequest) {
		String txnId = null;
		Optional<UserDetails> userOptional = userDetailsRepo.findById(transferRequest.getUserId());
		if (userOptional.isEmpty()) {
			return "User Id not available!";
		}
		Account account = accountRepo.findByAccNumber(transferRequest.getFromAccountNo());
		if (account == null) {
			return "From Account Details not found!";
		} else {
			int comparisonResult = account.getOpeningBalance().compareTo(transferRequest.getTransAmount());
			if (comparisonResult >= 0) {
				BigDecimal remainBalance = account.getOpeningBalance().subtract(transferRequest.getTransAmount());
				account.setOpeningBalance(remainBalance);
				account.setUpdatedDate(new Date());

				Transaction trans = Transaction.builder().transId(genarateNumber()).transType("DEBIT")
						.mediumOfTrans(transferRequest.getMediumOfTrans()).account(account)
						.transAmount(transferRequest.getTransAmount()).dot(new Date()).user(userOptional.get())
						.toAccountNo(transferRequest.getToAccountNo()).balance(remainBalance)
						.comments(transferRequest.getComments()).build();

				txnId = transactionRepo.save(trans).getTransId().toString();
				applicationEventPublisher.publishEvent(new TransferServiceEvent(this, transferRequest.getFromAccountNo(),transferRequest.getToAccountNo(),transferRequest.getTransAmount()));
				accountRepo.save(account);
			} else {
				return "The Money in your account is not enough for this transaction";
			}
		}

		return txnId;
	}

	@Override
	public List<AccountType> getAccTypes() {
		return accountTypeRepo.findAllByOrderByAccTypeIdAsc();
	}

	@Override
	public List<Branch> getAllBranches() {
		return branchRepo.findAll();
	}

	@Override
	public List<Account> getAccNosByAccTypeId(Long accTypeId) {
		return accountRepo.findByAcctypeAccTypeId(accTypeId);
	}

	@Override
	public List<Transaction> getTransHistoryById(Long accNo) {

		return transactionRepo.findByAccountAccNumber(accNo);
	}

}
