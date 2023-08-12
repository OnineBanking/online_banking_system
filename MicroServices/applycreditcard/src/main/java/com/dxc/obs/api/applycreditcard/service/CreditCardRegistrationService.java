package com.dxc.obs.api.applycreditcard.service;

import java.util.Optional;

import com.dxc.obs.api.applycreditcard.model.CreditCardRegistartion;

public interface CreditCardRegistrationService {
	
	public CreditCardRegistartion saveCreditCardApplication(CreditCardRegistartion ccApplication);
	
	public Optional<CreditCardRegistartion> fetchCreditCardApplication(Long ccnumber);
}
