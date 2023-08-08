package com.dxc.obs.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseCookie;

import com.dxc.obs.api.entity.AccountType;
import com.dxc.obs.api.entity.Branch;
import com.dxc.obs.api.entity.UserDetails;
import com.dxc.obs.api.payload.request.UserRequest;
import com.dxc.obs.api.payload.request.loginRequest;

public interface UserService {

	String registerUser(UserRequest userRequest);

	String login(loginRequest loginRequest);

	ResponseCookie logout();


	Optional<UserDetails> getUserByUsername(String username);

	List<Branch> getAllBranches();

	List<AccountType> getAllAccTypes();
	

}
