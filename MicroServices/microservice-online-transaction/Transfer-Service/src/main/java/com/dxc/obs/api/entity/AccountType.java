package com.dxc.obs.api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dxc.obs.api.entity.Account.AccountBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name ="ACCOUNT_TYPES")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accType_seq")
    @SequenceGenerator(name = "accType_seq", sequenceName = "ACCTYPE_SEQ", allocationSize = 1)
	private Long accTypeId;
	
	private String accTypeName;

}
