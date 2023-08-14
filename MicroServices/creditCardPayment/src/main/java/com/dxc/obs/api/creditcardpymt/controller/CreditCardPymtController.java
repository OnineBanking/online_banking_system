package com.dxc.obs.api.creditcardpymt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.obs.api.creditcardpymt.model.CreditCardPymtData;
import com.dxc.obs.api.creditcardpymt.service.CreditCardPymtService;

@RestController
@CrossOrigin
@RequestMapping("/creditCardPymt")
public class CreditCardPymtController {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditCardPymtController.class);
	
	@Autowired
	private CreditCardPymtService service;
	
	@RequestMapping("/outStandingAmt")
	public CreditCardPymtData creditCardPayment(@RequestBody CreditCardPymtData ccPymt) {
		logger.info("Credit card Payment service started");
		return service.creditCardPayment(ccPymt);
	}

}
