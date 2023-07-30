package com.dxc.olbs.creditcardregistration.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.olbs.creditcardregistration.dao.CreditCardRegistrationDao;
import com.dxc.olbs.creditcardregistration.model.CreditCardRegistartion;
import com.dxc.olbs.creditcardregistration.service.CreditCardRegistrationService;

@Service
public class CreditCardRegistrationServiceImpl implements CreditCardRegistrationService {
	
	@Autowired
	private CreditCardRegistrationDao dao;

	@Override
	public CreditCardRegistartion saveCreditCardApplication(CreditCardRegistartion ccApplication) {
		return dao.save(ccApplication);
	}
	
	@Override
	public Optional<CreditCardRegistartion> fetchCreditCardApplication(Long ccnumber){
		return dao.findById(ccnumber); 
	}
}
