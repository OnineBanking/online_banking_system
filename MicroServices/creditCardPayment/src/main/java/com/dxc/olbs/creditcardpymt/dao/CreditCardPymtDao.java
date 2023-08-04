package com.dxc.olbs.creditcardpymt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.olbs.creditcardpymt.model.CreditCardPymtData;

@Repository
public interface CreditCardPymtDao extends JpaRepository<CreditCardPymtData, Long>{

}
