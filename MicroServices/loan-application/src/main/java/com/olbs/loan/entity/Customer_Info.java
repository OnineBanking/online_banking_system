package com.olbs.loan.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="CUSTOMER_INFO")
@NoArgsConstructor
@AllArgsConstructor
public class Customer_Info {
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTID")
	 @SequenceGenerator(name = "CUSTID", sequenceName = "CUSTID", allocationSize = 1)
	 @Column(name="CUSTID")
	private int custid;
		private String fname;
		private String mname;
		private String ltname;
		private String city;
		private double mobileno;
		private String occupation;
		private Date dob;
		private String email;
		public int getCustid() {
			return custid;
		}
		public void setCustid(int custid) {
			this.custid = custid;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getMname() {
			return mname;
		}
		public void setMname(String mname) {
			this.mname = mname;
		}
		public String getLtname() {
			return ltname;
		}
		public void setLtname(String ltname) {
			this.ltname = ltname;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public double getMobileno() {
			return mobileno;
		}
		public void setMobileno(double mobileno) {
			this.mobileno = mobileno;
		}
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		
		

}
