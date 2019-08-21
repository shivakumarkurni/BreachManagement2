package com.breach.repository;
/***
 * @author Anuradha
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.breach.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	public UserRole findByRoleId(Integer roleId);

}
