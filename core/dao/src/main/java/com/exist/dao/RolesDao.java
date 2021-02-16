package com.exist.dao;
import com.exist.model.Roles;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

@Repository
public interface RolesDao extends JpaRepository<Roles, Integer> {
	// @Query("Select r From Role r Where r.role = ?1 and r.roleId != ?2")
	// Optional<Roles> findRoleByRoleEdit(String role,int roleId);
}
