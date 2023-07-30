package com.dxc.olbs.creditcardregistration.service;

import java.util.Optional;

import com.dxc.olbs.creditcardregistration.model.CreditCardRegistartion;

public interface CreditCardRegistrationService {
	
	public CreditCardRegistartion saveCreditCardApplication(CreditCardRegistartion ccApplication);
	
	public Optional<CreditCardRegistartion> fetchCreditCardApplication(Long ccnumber);
}
