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
		Optional<Roles> role = rolesdao.findById(id);
		return role.get();
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
			List<Roles> role = person.get().getPersonRole();
			role.add(roleToAdd.get());
			Person personToAddRole = person.get();
			personToAddRole.setRole(role);
			return personDao.save(personToAddRole);
		}		
		
	}

	public void deletePersonRole(int personId, int roleId) {

		Optional<Person> person = personDao.findById(personId);
		List<Roles> role = person.get().getPersonRole();
		Iterator<Roles> iterate = role.iterator();
		while(iterate.hasNext()) {
			Roles roleToBeDeleted = iterate.next();
			if(roleToBeDeleted.getId() == roleId) {
				iterate.remove();
			}
		}
		Person updatePersonRole = person.get();
		updatePersonRole.setRole(role);
		personDao.save(updatePersonRole);
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