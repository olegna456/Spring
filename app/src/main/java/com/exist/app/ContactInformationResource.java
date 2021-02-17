package com.exist.app;

import com.exist.model.Person;
import com.exist.model.ContactInformation;
import com.exist.service.ContactInformationService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.List;

@RestController
@ComponentScan(basePackageClasses = {com.exist.service.ContactInformationService.class})
@EntityScan("com.exist.model")
@RequestMapping(path="api/contact")
public class ContactInformationResource {
	
	@Autowired
	private final ContactInformationService cis;

	
	public ContactInformationResource(ContactInformationService cis) {
		this.cis = cis;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable("id") int id) {
		cis.deleteContactInformation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/addContact/{personId}")
	public ResponseEntity<Person> addContactInformationToPerson(@PathVariable("personId") int personId, @RequestBody ContactInformation contactinformation) {
		Person personAddContact = cis.addContactInformationToPerson(personId, contactinformation);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/editContact/{contactId}")
	public ResponseEntity<ContactInformation> editContactInformation(@PathVariable("contactId") int contactId, @RequestBody ContactInformation contactinformation) {
		ContactInformation editPerson = cis.editContactInformation(contactId, contactinformation);
		return new ResponseEntity<>(editPerson, HttpStatus.OK);
	}

	// -@GetMapping("/getContactPerson/{personId}")

}
	