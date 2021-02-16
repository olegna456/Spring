package com.exist.app;

import com.exist.model.Person;
import com.exist.model.Roles;
import com.exist.model.ContactInformation;
import com.exist.service.PersonService;
import com.exist.service.RolesService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;

@RestController
@ComponentScan(basePackageClasses = {com.exist.service.PersonService.class, com.exist.service.RolesService.class})
@EntityScan("com.exist.model")
@RequestMapping(path="api")

public class PersonResource {

	private final PersonService personService;
	private final RolesService rs;

	@Autowired
	public PersonResource(PersonService personService, RolesService rs) {
		this.personService = personService;
		this.rs = rs;
	}

	//okay
	@PostMapping("/addPerson")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		Person newPerson = personService.addPerson(person);
		return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
	}
	//okay
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPerson() {
		List<Person> persons = personService.getAllPerson();
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	//okay
	@PutMapping("/update/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
		Person updatePerson = personService.updatePerson(id, person);
		return new ResponseEntity<>(updatePerson, HttpStatus.OK);
	}
	//okay
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") int id) {
		personService.deletePersonById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/personBy/{choice}")
	public ResponseEntity<List<Person>> getPersonBy(@PathVariable("choice") int choice) {
		List<Person> allPersons = personService.getByOrder(choice);
		return new ResponseEntity<>(allPersons, HttpStatus.OK);
	}
	//okay
	// @PostMapping("/addContact/{id}")
	// public ResponseEntity<Person> addContactInformationToPerson(@PathVariable("id") int id, @RequestBody ContactInformation contactinformation) {
	// 	Person personAddContact = personService.addContactInformationToPerson(id, contactinformation);
	// 	return new ResponseEntity<>(personAddContact, HttpStatus.CREATED);
	// }

	// @PutMapping("updateContact/{personId}/{contactId}/{choice}")
	// public ResponseEntity<Person> updateContactInformation(@PathVariable("personId") int personId, @PathVariable("contactId") int contactId,@PathVariable int choice, @RequestBody ContactInformation contactinformation) {
	// 	if(choice == 1) {
	// 		ContactInformation updateContact = personService.updateContactInformation(personId, contactId, contactinformation);
	// 	}
	// 	return ResponseEntity<>(HttpStatus.OK);
	// }

	// @PostMapping("addContact/{id}") 
	// public void addContact(@PathVariable("id") int id, @RequestBody ContactInformation contactinformation) {
	// 	personService.addContactInformationToPerson(id, contactinformation);
	// }

	// @PostMapping("addRolePerson/{personId}/{roleId}")
	// public void addRoleToPerson(@PathVariable("personId") int personId, @PathVariable("roleId") int roleId) {
	// 	Roles roleToAdd = rs.findById(roleId);
	// 	personService.addPersonRole(roleToAdd, personId);
	// }

	// @PutMapping("/delete/{personId}/{roleId}")
	// public void deletePersonRole(@PathVariable("personId") int personId, @PathVariable("roleId") int roleId) {
	// 	personService.deletePersonRole(personId, roleId);
	// }

	// @GetMapping("/person/{orderId}")
	// public List<Person> listBy(@PathVariable("orderId") int orderId) {
	// 	List<Person> person = new ArrayList<>();
	// 	if(orderId == 1) {
	// 		person = personService.getPersonsByDateHired();
	// 	} else if (orderId == 2) {
	// 		person = personService.getPersonsByLastName();
	// 	} else {
	// 		person = personService.getPersonsByGwa();
	// 	}
	// 	return person;
	// }

	// @PutMapping("/updateContact/{personId}/{contactId}")
	// public void updateContact(@PathVariable("personId") int personId, @PathVariable("contactId") int contactId, @RequestBody Contactinformation contactinformation) {
	// 	personService.updateContact(personId, contactId, contactinformation);
	// }

}
