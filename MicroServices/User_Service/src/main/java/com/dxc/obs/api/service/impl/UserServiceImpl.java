package com.dxc.obs.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.obs.api.entity.Account;
import com.dxc.obs.api.entity.AccountType;
import com.dxc.obs.api.entity.Branch;
import com.dxc.obs.api.entity.UserDetails;
import com.dxc.obs.api.exception.BadRequestException;
import com.dxc.obs.api.payload.request.UserRequest;
import com.dxc.obs.api.payload.request.loginRequest;
import com.dxc.obs.api.repository.AccountRepository;
import com.dxc.obs.api.repository.AccountTypeRepository;
import com.dxc.obs.api.repository.BranchRepository;
import com.dxc.obs.api.repository.UserRepository;
import com.dxc.obs.api.service.UserService;
import com.dxc.obs.api.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BranchRepository branchRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	AccountTypeRepository accountTypeRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AccountRepository accountRepo;

	@Override
	public String registerUser(UserRequest userRequest) {
		Long userId = null;
		String accountNo = null;
		log.info("Register User method -Start");

		if (userRepository.existsByUserName(userRequest.getUserName())) {
			log.debug("Error: Username is already in used!");
			throw new BadRequestException("Error: Username is already in used!");
		}

		if (userRepository.existsByEmail(userRequest.getEmail())) {
			log.debug("Error: Email is already in use!");
			throw new BadRequestException("Error: Email is already in use!");
		}

		Optional<Branch> branchOptional = branchRepo.findByBranchId(userRequest.getBranch());
		if (branchOptional.isEmpty()) {
			throw new BadRequestException("Error: Branch details not found!");
		}
		Optional<AccountType> accTypeOptional = accountTypeRepo.findById(userRequest.getAccountType());
		if (accTypeOptional.isEmpty()) {
			throw new BadRequestException("Error: Account type not found!");
		}

		UserDetails user = UserDetails.builder().userName(userRequest.getUserName()).email(userRequest.getEmail())
				.gender(userRequest.getGender()).password(passwordEncoder.encode(userRequest.getPassword()))
				.phoneNumber(userRequest.getPhoneNumber()).build();
		userId = userRepository.save(user).getUserId();

		if (userId != null) {
			Account account = Account.builder().accNumber(genarateNumber()).acctype(accTypeOptional.get())
					.accStatus("open").branch(branchOptional.get()).user(user)
					.openingBalance(userRequest.getOpeningBalance()).aod(new Date()).build();
			accountNo = accountRepo.save(account).getAccNumber().toString();
		}
		log.info("Register User method-End");

		log.info("Account {} created!", accountNo);
		return accountNo;

	}

	private Long genarateNumber() {
		Random random = new Random();
		int randomNumber = 10000000 + random.nextInt(90000000);
		return Long.valueOf(randomNumber);
	}

	@Override
	public String login(loginRequest loginRequest) {
		log.info("Login service - start");
		Optional<UserDetails> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

		if (optionalUser.isEmpty()) {
			log.debug("User Not Found !");
			throw new BadRequestException("User Not Found !");
		}
		if (passwordEncoder.matches(loginRequest.getPassword(), optionalUser.get().getPassword())) {
			log.info("Login service - end");
			return jwtUtil.generateAccessToken(optionalUser.get());
		} else {
			log.debug("Invalid Username Or Password ");
			throw new BadRequestException("Invalid Username Or Password ");
		}
	}

	@Override
	public ResponseCookie logout() {
		log.info("Logout service called");
		ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
		return cookie;

	}

	@Override
	public List<Branch> getAllBranches() {
		return branchRepo.findAll();
	}

	@Override
	public Optional<UserDetails> getUserByUsername(String username) {

		Optional<UserDetails> optionalUser = userRepository.findByEmail(username);

		if (optionalUser.isEmpty()) {
			log.debug("User Not Found !");
			throw new BadRequestException("User Not Found !");
		} else {
			return optionalUser;
		}
	}

	@Override
	public List<AccountType> getAllAccTypes() {
		return accountTypeRepo.findAll();
	}

}
