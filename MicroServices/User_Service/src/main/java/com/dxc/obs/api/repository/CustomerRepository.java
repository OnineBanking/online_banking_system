package com.dxc.obs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.obs.api.entity.Customer;
import com.dxc.obs.api.entity.UserDetails;

public interface CustomerRepository extends JpaRepository<Customer, Long>{



}
