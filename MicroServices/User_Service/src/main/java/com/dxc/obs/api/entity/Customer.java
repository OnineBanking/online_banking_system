package com.dxc.obs.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dxc.obs.api.entity.UserDetails.UserDetailsBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name ="CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
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
