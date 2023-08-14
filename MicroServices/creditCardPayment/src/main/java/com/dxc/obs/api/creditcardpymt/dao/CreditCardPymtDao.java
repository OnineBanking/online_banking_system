package com.dxc.obs.api.creditcardpymt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.obs.api.creditcardpymt.model.CreditCardPymtData;

@Repository
public interface CreditCardPymtDao extends JpaRepository<CreditCardPymtData, Long>{

}
