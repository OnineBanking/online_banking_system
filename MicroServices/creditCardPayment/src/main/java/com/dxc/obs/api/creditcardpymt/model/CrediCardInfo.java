package com.dxc.obs.api.creditcardpymt.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "credit_card_pymt")
public class CrediCardInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2960938093994052244L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_credit_card_pymt_SEQ")
	@SequenceGenerator(name="TAB_credit_card_pymt_SEQ", sequenceName="TAB_credit_card_pymt_SEQ", allocationSize=1)
	@Column(name = "cc_pymt_info_id")
	private int ccInfoId;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TXN_NUMBER", referencedColumnName = "tnumber")
	CreditCardPymtData creditCardPymtData;
	
	@Column(name = "CC_HOLDER_NAME")
	private String fullName;

	@Column(name = "cvv")
	private int cvv;
	
	@Column(name = "cc_exp_mm")
	private int expMonth;
	
	@Column(name = "cc_exp_yyyy")
	private int expYear;

	public CreditCardPymtData getCreditCardPymtData() {
		return creditCardPymtData;
	}

	public void setCreditCardPymtData(CreditCardPymtData creditCardPymtData) {
		this.creditCardPymtData = creditCardPymtData;
	}

	public int getCcInfoId() {
		return ccInfoId;
	}

	public void setCcInfoId(int ccInfoId) {
		this.ccInfoId = ccInfoId;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
}
