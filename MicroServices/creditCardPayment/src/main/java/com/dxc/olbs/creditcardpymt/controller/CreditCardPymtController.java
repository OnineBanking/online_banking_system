package com.dxc.olbs.creditcardpymt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.olbs.creditcardpymt.model.CreditCardPymtData;
import com.dxc.olbs.creditcardpymt.service.CreditCardPymtService;

@RestController
@RequestMapping("/api")
public class CreditCardPymtController {
	
	@Autowired
	private CreditCardPymtService service;
	
	@RequestMapping("/creditCardPymt")
	public CreditCardPymtData creditCardPayment(@RequestBody CreditCardPymtData ccPymt) {
		return service.creditCardPayment(ccPymt);
	}

}
