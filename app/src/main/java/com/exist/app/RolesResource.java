package com.exist.app;

import com.exist.model.Roles;
import com.exist.model.Person;
import com.exist.service.RolesService;
import com.exist.service.PersonService;
import com.exist.util.*;
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
	public ResponseEntity<?> deleteRole(@PathVariable("roleId") int roleId) {
		try {
			rs.deleteRole(roleId);
			return new ResponseEntity<>("Role with id: " + roleId + " successfully deleted", HttpStatus.OK);
		}catch(RoleNotFoundException ex) {
			String message = "Role id not found";
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}		
	}

	@PostMapping("addRoleToPerson/{personId}/{roleId}")
	public ResponseEntity<Person> addRoleToPerson(@PathVariable("personId") int personId, @PathVariable("roleId") int roleId) {
		// return rs.addRoleToPerson(personId, roleId);
		return new ResponseEntity<>(rs.addRoleToPerson(personId, roleId), HttpStatus.OK);
	}

	@DeleteMapping("deleteRoleFromPerson/{personId}/{roleId}")
	public ResponseEntity<Person> deleteRoleFromPerson(@PathVariable("personId") int personId, @PathVariable("roleId") int roleId) {
		Person personTobeDeletedRole = rs.deleteRoleFromPerson(personId, roleId);
		return new ResponseEntity<>(personTobeDeletedRole, HttpStatus.OK);
	}	
}
