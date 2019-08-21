package com.breach.repository;
/***
 * @author Anuradha
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
	public UserDetails findByName(String name);
	

}
