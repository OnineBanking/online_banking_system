package com.dxc.obs.api.applycreditcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.applycreditcard.model.CreditCardRegistartion;

public interface CreditCardRegistrationDao extends JpaRepository<CreditCardRegistartion, Long>{
}
