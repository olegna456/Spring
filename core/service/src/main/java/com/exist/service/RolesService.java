package com.exist.service;

import com.exist.dao.RolesDao;
import com.exist.model.Roles;
import com.exist.dao.PersonDao;
import com.exist.model.Person;
import com.exist.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.stream.*;
import java.util.stream.Collectors;
import java.util.*;

@Service
public class RolesService {

	
	private final RolesDao rolesdao;
	private final PersonDao personDao;
	@Autowired
	
	public RolesService(RolesDao rolesdao, PersonDao personDao) {
		this.rolesdao = rolesdao;
		this.personDao = personDao;
	}

	public Roles addRole(Roles role) {
		return rolesdao.save(role);
	}

	public Roles findById(int id) {
		Roles role = rolesdao.findById(id).get();
		return role;
	}

	public List<Roles> getAllRoles() {
		List<Roles> role = rolesdao.findAll();
		return setRoleToNull(role);
	}

	public List<Roles> setRoleToNull(List<Roles> list) {
		list.stream()
			.forEach(role -> {
				role.setPersonRole(null);				
			});
		return list;
	}

	public Roles updateRole(int roleId, Roles role) {
		role.setRoleId(roleId);
		return rolesdao.save(role);
	}

	public void deleteRole(int roleId) {
		rolesdao.deleteById(roleId);
	}

	public Person addRoleToPerson(int personId, int roleId) {
		boolean duplicate = checkRoleDuplicate(personId, roleId);
		if(duplicate) {
			throw new ResourceAlreadyExists("Role already Existing on chosen person.");
		} else {
			Optional<Roles> roleToAdd = rolesdao.findById(roleId);
			Optional<Person> person = personDao.findById(personId);
			Person personToAddRole = person.get();
			List<Roles> role = personToAddRole.getPersonRole();
			role.add(roleToAdd.get());
			role = setRoleToNull(role);			
			personToAddRole.setRole(role);			
			return personDao.save(personToAddRole);
		}		
		
	}

	public Person deleteRoleFromPerson(int personId, int roleId) {
		Person person = personDao.findById(personId).get();
		List<Roles> role = person.getPersonRole();
		Iterator<Roles> iterate = role.iterator();
		while(iterate.hasNext()) {
			Roles currentRole = iterate.next();
			if(currentRole.getId() == roleId) {
				iterate.remove();
			}
		}
		person.setRole(role);
		return personDao.save(person);
	}

	

	public boolean checkRoleDuplicate(int personId, int roleId) {
		boolean duplicate = false;
		Optional<Person> personCheck = personDao.findById(personId);
		List<Roles> role = personCheck.get().getPersonRole();
		Iterator<Roles> iterate = role.iterator();
		while(iterate.hasNext()) {
			Roles roleToCheck = iterate.next();
			if(roleToCheck.getId() == roleId) {
				duplicate = true;
			}
		}
		return duplicate;
	}
}