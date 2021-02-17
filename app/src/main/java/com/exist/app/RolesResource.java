package com.exist.app;

import com.exist.model.Roles;
import com.exist.model.Person;
import com.exist.service.RolesService;
import com.exist.service.PersonService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util. *;

@RestController
@ComponentScan(basePackageClasses = {com.exist.service.PersonService.class, com.exist.service.RolesService.class})
@EntityScan("com.exist.model")
@RequestMapping("api/role")
public class RolesResource {

	private final RolesService rs;
	private final PersonService ps;

	@Autowired
	public RolesResource(RolesService rs, PersonService ps) {
		this.rs = rs;
		this.ps = ps;
	}

	@PostMapping("/addRole")
	public ResponseEntity<Roles> addRole(@RequestBody Roles role) {
		Roles addRole = rs.addRole(role);
		return new ResponseEntity<>(addRole, HttpStatus.CREATED);
	}

	@GetMapping("/allRoles")
	public ResponseEntity<List<Roles>> getAllRoles() {
		List<Roles> role = rs.getAllRoles();
		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	@PutMapping("/updateRole/{roleId}")
	public ResponseEntity<Roles> updateRole(@PathVariable("roleId") int roleId, @RequestBody Roles role) {
		Roles roleUpdate = rs.updateRole(roleId, role);
		return new ResponseEntity<>(roleUpdate, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{roleId}")
	public ResponseEntity<Roles> deleteRole(@PathVariable("roleId") int roleId) {
		rs.deleteRole(roleId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("addRoleToPerson/{personId}/{roleId}")
	public ResponseEntity<Person> addRoleToPerson(@PathVariable("personId") int personId, @PathVariable("roleId") int roleId) {
		Person addRolePerson = rs.addRoleToPerson(personId, roleId);
		return new ResponseEntity<>(addRolePerson, HttpStatus.OK);
	}

	@DeleteMapping("deleteRoleFromPerson/{personId}/{roleId}")
	public ResponseEntity<Person> deleteRoleFromPerson(@PathVariable("personId") int personId, @PathVariable("roleId") int roleId) {
		Person personTobeDeletedRole = rs.deleteRoleFromPerson(personId, roleId);
		return new ResponseEntity<>(personTobeDeletedRole, HttpStatus.OK);
	}	
}
