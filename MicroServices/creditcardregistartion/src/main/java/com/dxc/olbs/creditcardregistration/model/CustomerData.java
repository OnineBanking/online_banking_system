package com.dxc.olbs.creditcardregistration.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_INFO")
public class CustomerData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERSEQ")
    @SequenceGenerator(sequenceName = "CUSTOMERSEQ", allocationSize = 1, name = "CUSTOMERSEQ")
    @Column(name = "CUSTID")
	Long custId;
	
	@Column(name = "FNAME")
	String fName;
	
	@Column(name = "MNAME")
	String mName;
	
	@Column(name = "LTNAME")
	String lName;
	
	@Column(name = "CITY")
	String city;
	
	@Column(name = "MOBILENO")
	String mobileNo;
	
	@Column(name = "OCCUPATION")
	String occupation;
	
	@Column(name = "DOB")
	String dob;
	
	@Column(name = "EMAIL")
	String email;
		
	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
