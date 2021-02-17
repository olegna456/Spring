package com.exist.service;
import com.exist.dao.ContactInformationDao;
import com.exist.model.ContactInformation;
import com.exist.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.*;
import com.exist.model.Person;

@Service
// @EnableJpaRepositories("com.exist.dao")
@ComponentScan(basePackages = {"com.exist.dao"})
public class ContactInformationService {

	@Autowired
	private final ContactInformationDao contactInformationDao;
	private final PersonDao personDao;

	public ContactInformationService(ContactInformationDao contactInformationDao, PersonDao personDao) {
		this.contactInformationDao = contactInformationDao;
		this.personDao = personDao;
	}

	public ContactInformation addContact(ContactInformation contactinformation) {
		return contactInformationDao.save(contactinformation);
	}

	public void deleteContactInformation(int id) {
		contactInformationDao.deleteById(id);
	}

	public Person addContactInformationToPerson(int personId, ContactInformation contactinformation) {
		Person person = personDao.findById(personId).get();
		List<ContactInformation> addContact = person.getContactInformation();
		addContact.add(contactinformation);
		person.setContactInformation(addContact);
		return personDao.save(person);
	}

	public ContactInformation editContactInformation(int contactId, ContactInformation contactinformation) {			
		contactinformation.setContactId(contactId);
		return contactInformationDao.save(contactinformation);

	}

	// public void updateContact(int id, ContactInformation contactinformation) {
	// 	Optional<ContactInformation> contactToUpdate = contactInformationDao.findById(id);
	// 	// contactToUpdate.setContactId(id);
	// 	ContactInformation con = contactToUpdate.get();
	// 	con.setMobileNumber(contactinformation.getMobileNumber());
	// 	con.setLandline(contactinformation.getLandline());
	// 	con.setEmail(contactinformation.getEmail());
	// 	contactInformationDao.save(con);
	// 	// ContactInformation contactInformationToUpdate = contactInformationDao.findById(id).orElse(null);
	// 	// contactInformationToUpdate.setMobileNumber(contactinformation.getMobileNumber());
	// 	// contactInformationToUpdate.setLandline(contactinformation.getLandline());
	// 	// contactInformationToUpdate.setEmail(contactinformation.getEmail());
	// 	// return contactInformationDao.save(contactInformationToUpdate);
	// }
}