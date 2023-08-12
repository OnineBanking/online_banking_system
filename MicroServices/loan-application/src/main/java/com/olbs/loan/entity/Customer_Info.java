package com.olbs.loan.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
public class Customer_Info {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
    @SequenceGenerator(name = "cust_seq", sequenceName = "CUST_SEQ", allocationSize = 1)
	private Long custId;
	
	private String firstName;
	
	private String lastName;
	
	private Long phoneNumber;
	
	private String city;
	
	private Date dob;
	
	private String occupation;
	
	private String gender;
	
	@ManyToOne
	private UserDetails user;
		
		

}
