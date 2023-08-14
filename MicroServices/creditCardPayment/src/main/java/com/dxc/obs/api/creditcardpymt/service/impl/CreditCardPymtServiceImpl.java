package com.dxc.obs.api.creditcardpymt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.obs.api.creditcardpymt.dao.CreditCardPymtDao;
import com.dxc.obs.api.creditcardpymt.model.CreditCardPymtData;
import com.dxc.obs.api.creditcardpymt.service.CreditCardPymtService;

@Service
public class CreditCardPymtServiceImpl implements CreditCardPymtService{
	
	@Autowired
	private CreditCardPymtDao dao = null;

	@Override
	public CreditCardPymtData creditCardPayment(CreditCardPymtData ccPymt) {
		CreditCardPymtData ccPymtData = null;
		try {
			ccPymtData = dao.save(ccPymt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ccPymtData;
	}

}
