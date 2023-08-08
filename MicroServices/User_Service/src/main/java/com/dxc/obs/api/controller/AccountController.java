package com.dxc.obs.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.obs.api.entity.Account;
import com.dxc.obs.api.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	 @GetMapping("/getAccInfoById")
	    public ResponseEntity<ResponseEntity<Optional<Account>>> getAccInfoById(Long userId) {
		 log.info("getAccInfoById service - start");
		 Optional<Account> accInfo = accountService.getAccInfoById(userId);

	        if (accInfo != null) {
	        	return ResponseEntity.ok().body(new ResponseEntity<>(accInfo,HttpStatus.OK));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
