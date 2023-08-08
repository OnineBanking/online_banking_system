package com.dxc.obs.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dxc.obs.api.entity.UserDetails;

public interface UserRepository extends  JpaRepository<UserDetails,Long>{

	Optional<UserDetails> findByEmail(String email);
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserDetails u WHERE u.userName = ?1")
	boolean existsByUserName(String userName);
	@Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserDetails u WHERE u.email = ?1")
	boolean existsByEmail(String email);

}
