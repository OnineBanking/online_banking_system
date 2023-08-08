package com.dxc.obs.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name ="BRANCH")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brnh_seq")
    @SequenceGenerator(name = "brnh_seq", sequenceName = "BRNH_SEQ", allocationSize = 1)
	private Long branchId;
	
	private String branchName;
	
	private String branchCity;
	
	
	
	

}
