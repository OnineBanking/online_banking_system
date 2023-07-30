package com.dxc.olbs.creditcardregistration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.olbs.creditcardregistration.model.CreditCardRegistartion;
import com.dxc.olbs.creditcardregistration.service.CreditCardRegistrationService;

@RestController
@RequestMapping("/api")
public class CreditCardRegistartionController {
	
	@Autowired
	CreditCardRegistrationService service;
	
	@RequestMapping("/saveCreditCardApplication")
	public CreditCardRegistartion saveCreditCardApplication(@RequestBody CreditCardRegistartion creditCardApp) {
		return service.saveCreditCardApplication(creditCardApp);
	}
	
	@GetMapping("/fetchCreditCardApplication/{id}")
	public Optional<CreditCardRegistartion> fetchCreditCardApplication(@PathVariable("id") long ccnumber) {
		return service.fetchCreditCardApplication(ccnumber);
	}
}
