package com.exist.service;

import com.exist.dao.RolesDao;
import com.exist.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@ComponentScan(basePackages ={"com.exist.dao"})
public class RolesService {

	
	private final RolesDao rolesdao;
	@Autowired
	
	public RolesService(RolesDao rolesdao) {
		this.rolesdao = rolesdao;
	}

	public Roles addRole(Roles role) {
		return rolesdao.save(role);
	}

	public Roles findById(int id) {
		Optional<Roles> role = rolesdao.findById(id);
		return role.get();
	}

	public List<Roles> getAllRoles() {
		return rolesdao.findAll();
	}

	public Roles updateRole(int roleId, Roles role) {
		role.setRoleId(roleId);
		return rolesdao.save(role);
	}

	public void deleteRole(int roleId) {
		rolesdao.deleteById(roleId);
	}

	

}