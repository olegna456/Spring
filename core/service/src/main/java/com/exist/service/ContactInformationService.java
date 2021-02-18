package com.exist.service;
import com.exist.dao.ContactInformationDao;
import com.exist.model.ContactInformation;
import com.exist.dao.PersonDao;
import com.exist.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.*;
import com.exist.model.Person;

@Service
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
}