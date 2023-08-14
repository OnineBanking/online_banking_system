package com.dxc.obs.api.entity;

import jakarta.persistence.*;

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
