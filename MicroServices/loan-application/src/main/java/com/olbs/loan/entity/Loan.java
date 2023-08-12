package com.olbs.loan.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOAN")
public class Loan {

    @Id
 	private Long loanId;
    
    @ManyToOne
    private Branch bid; 
    
    private BigDecimal loan_amount;
    
    private double loanTenure;
    
    private BigDecimal loanEmi; 
    
    private String status;
    
    private String interest;
    
    @ManyToOne
    private Customer_Info cust;
    
    
		
}
