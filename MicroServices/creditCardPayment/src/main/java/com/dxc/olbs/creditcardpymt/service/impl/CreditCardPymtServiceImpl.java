package com.dxc.olbs.creditcardpymt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.olbs.creditcardpymt.dao.CreditCardPymtDao;
import com.dxc.olbs.creditcardpymt.model.CreditCardPymtData;
import com.dxc.olbs.creditcardpymt.service.CreditCardPymtService;

@Service
public class CreditCardPymtServiceImpl implements CreditCardPymtService{
	
	@Autowired
	private CreditCardPymtDao dao = null;

	@Override
	public CreditCardPymtData creditCardPayment(CreditCardPymtData ccPymt) {
		
		return dao.save(ccPymt);
	}

}
