package com.dxc.obs.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.obs.api.entity.AccountType;
import com.dxc.obs.api.entity.Branch;
import com.dxc.obs.api.entity.UserDetails;
import com.dxc.obs.api.payload.request.UserRequest;
import com.dxc.obs.api.payload.request.loginRequest;
import com.dxc.obs.api.payload.response.Response;
import com.dxc.obs.api.payload.response.ServiceResponse;
import com.dxc.obs.api.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


	private final UserService userService;
	
	
	//@ApiOperation("user signup")
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
log.info("signup controller - start");
		String accNo  = userService.registerUser(userRequest);
		return ResponseEntity.ok().body(new ResponseEntity<>(ServiceResponse.builder().data(accNo).responseMsg("User Registered Successfully !!").build(),
				HttpStatus.CREATED));

	}
	//@ApiOperation("user login")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody loginRequest loginRequest) {
		log.info("login controller - start");
		String token = userService.login(loginRequest);

		return ResponseEntity.ok().body(new ResponseEntity<>(Response.builder().token(token).responseMsg("Login Successfully.").build(),
				HttpStatus.OK));

	}
	//@ApiOperation("user signout")
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		log.info("signout controller - start");
		ResponseCookie cookie = userService.logout();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new ResponseEntity<>(
				ServiceResponse.builder().responseMsg("You've been signed out!!!").build(), HttpStatus.OK));
	}

	 @GetMapping("/getAllBranches")
	 public ResponseEntity<List<Branch>> getAllBranches(){
		 List<Branch> branches = userService.getAllBranches();
		 if(branches.isEmpty()) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		 }else {
			
			 return ResponseEntity.ok(branches); 
		 }
		
		 
		 
	 }
	 
	 @GetMapping("/getAllAccTypes")
	 public ResponseEntity<List<AccountType>> getAllAccTypes(){
		 List<AccountType> accTypes = userService.getAllAccTypes();
		 if(accTypes.isEmpty()) {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		 }else {
			
			 return ResponseEntity.ok(accTypes); 
		 }
		
		 
		 
	 }
	 
	 @GetMapping("/getUserByName")
	    public ResponseEntity<ResponseEntity<Optional<UserDetails>>> getUserByUsername(String username) {
		 log.info("getUserByUsername controller - start");
		 Optional<UserDetails> user = userService.getUserByUsername(username);

	        if (user != null) {
	        	return ResponseEntity.ok().body(new ResponseEntity<>(user,
	    				HttpStatus.OK));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
