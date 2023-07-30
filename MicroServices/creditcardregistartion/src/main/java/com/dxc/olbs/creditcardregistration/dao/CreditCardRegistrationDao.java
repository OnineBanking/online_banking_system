package com.dxc.olbs.creditcardregistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.olbs.creditcardregistration.model.CreditCardRegistartion;

public interface CreditCardRegistrationDao extends JpaRepository<CreditCardRegistartion, Long>{
}
