package com.exist.app;

import com.exist.model.Person;
import com.exist.service.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

@RestController
@RequestMapping(path="api")

public class PersonResource {

	private final PersonService personService;

	@Autowired
	public PersonResource(PersonService personService) {
		this.personService = personService;
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
	public ResponseEntity<?> updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
		Person updatePerson = personService.updatePerson(id, person);
		return new ResponseEntity<>(updatePerson, HttpStatus.OK);
	}
	//okay
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") int id) {
		personService.deletePersonById(id);
		return new ResponseEntity<>("Person with id " + id + " successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/personBy/{choice}")
	public ResponseEntity<List<Person>> getPersonBy(@PathVariable("choice") int choice) {
		List<Person> allPersons = personService.getByOrder(choice);
		return new ResponseEntity<>(allPersons, HttpStatus.OK);
	}
}
