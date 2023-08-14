package com.dxc.obs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.obs.api.entity.Account;
import com.dxc.obs.api.entity.AccountType;
import com.dxc.obs.api.entity.Branch;
import com.dxc.obs.api.entity.Transaction;
import com.dxc.obs.api.payload.request.AccountRequest;
import com.dxc.obs.api.payload.request.TransferRequest;
import com.dxc.obs.api.response.Response;
import com.dxc.obs.api.service.TransferService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	TransferService transferService;

	@PostMapping("/createAccType")
	public ResponseEntity<?> createAccType(@RequestParam String accType) {
		transferService.createAccType(accType);

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().responseMsg("Account Type Created Successfully !!").build(), HttpStatus.CREATED));
	}

	@GetMapping("/accTypes/all")

	public ResponseEntity<ResponseEntity<Response<Object>>> getAccTypes() {
		List<AccountType> accTypes = transferService.getAccTypes();

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().obj(accTypes).responseMsg("Account Types fetched Successfully !!").build(), HttpStatus.OK));

	}
	
	@GetMapping("/getAccNumbers")

	public ResponseEntity<ResponseEntity<Response<Object>>> getAccNosByAccTypeId(Long accTypeId) {
		List<Account> accNos = transferService.getAccNosByAccTypeId(accTypeId);

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().obj(accNos).responseMsg("Account Numbers fetched Successfully !!").build(), HttpStatus.OK));

	}

	@PostMapping("/createBranch")
	public ResponseEntity<?> createBranch(@RequestParam String branchName, @RequestParam String city) {
		transferService.createBranch(branchName, city);

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().responseMsg("Branch Created Successfully !!").build(), HttpStatus.CREATED));
	}
	
	@GetMapping("/branches/all")

	public ResponseEntity<ResponseEntity<Response<Object>>> getAllBranches() {
		List<Branch> branches = transferService.getAllBranches();

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().obj(branches).responseMsg("Branches fetched Successfully !!").build(), HttpStatus.OK));

	}

	@PostMapping("/createAccount")
	public ResponseEntity<?> createAccount(@RequestBody AccountRequest accountRequest) {
		String accNo = transferService.createAccount(accountRequest);

		return ResponseEntity.ok()
				.body(new ResponseEntity<>(
						Response.builder().id(accNo).responseMsg("Account Created Successfully !!").build(),
						HttpStatus.CREATED));
	}

	@GetMapping("/accInfoById")

	public ResponseEntity<ResponseEntity<Response<Object>>> findByAccInfoById(Long userId) {
		List<Account> account = transferService.findByAccInfoById(userId);

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().obj(account).responseMsg("Account Details fetched Successfully !!").build(), HttpStatus.OK));

	}

	@PostMapping("/debit")
	public ResponseEntity<?> moneyTransfer(@RequestBody TransferRequest transferRequest) {
		String transId = transferService.moneyTransfer(transferRequest);

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().id(transId).responseMsg("Amount Transfred Successfully !!").build(), HttpStatus.OK));
	}
	
	@GetMapping("/transHistoryByid")

	public ResponseEntity<ResponseEntity<Response<Object>>> getTransHistoryById(Long accNo) {
		List<Transaction> transHistory = transferService.getTransHistoryById(accNo);

		return ResponseEntity.ok().body(new ResponseEntity<>(
				Response.builder().obj(transHistory).responseMsg("Trasaction Details fetched Successfully !!").build(), HttpStatus.OK));

	}

}
