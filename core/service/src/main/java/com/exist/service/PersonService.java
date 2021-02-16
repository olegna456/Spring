package com.exist.service;
import com.exist.dao.PersonDao;
import com.exist.model.Person;
import com.exist.model.Roles;
import com.exist.model.ContactInformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@EnableJpaRepositories("com.exist.dao.PersonDao")
@ComponentScan(basePackages  = {"com.exist.dao"})
public class PersonService {
	
	@Autowired
	private final PersonDao personDao;

	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
	}

	public Person addPerson(Person person) {
		return personDao.save(person);
	}

	public List<Person> getAllPerson() {
		return personDao.findAll();
	}
	
	public Person updatePerson(int id, Person person) {
		Person personToUpdate = personDao.findById(id).orElse(null);
		personToUpdate.setFirstName(person.getFirstName());
		personToUpdate.setLastName(person.getLastName());
		personToUpdate.setMiddleName(person.getMiddleName());
		personToUpdate.setSuffix(person.getSuffix());
		personToUpdate.setTitle(person.getTitle());
		personToUpdate.setStreetNumber(person.getStreetNumber());
		personToUpdate.setBarangay(person.getBarangay());
		personToUpdate.setMunicipalityOrCity(person.getMunicipalityOrCity());
		personToUpdate.setZipcode(person.getZipcode());
		personToUpdate.setBirthday(person.getBirthday());
		personToUpdate.setDateHired(person.getDateHired());
		personToUpdate.setGwa(person.getGwa());
		personToUpdate.setEmployed(person.getEmployed());
		return personDao.save(personToUpdate);
	}

	public List<Person> getByOrder(int choice) {
		Optional<List<Person>> personByOrder;
		List<Person> personByOrderToReturn = new ArrayList<>();
		if(choice == 1) {
			personByOrder = personDao.getPersonOrderByDateHired();
			personByOrderToReturn = personByOrder.get();
		}else if(choice == 2) {

		} else if(choice == 3) {

		} else {
			return null;
		}
		return personByOrderToReturn;
	}

	public void deletePersonById(int id) {
		personDao.deleteById(id);
	}

	// public Person addContactInformationToPerson(int id, ContactInformation contactinformation) {
	// 	Person personToAddContact = personDao.findById(id).get();
	// 	List<ContactInformation> contactToAdd = personToAddContact.getContactInformation();
	// 	contactToAdd.add(contactinformation);
	// 	personToAddContact.setContactInformation(contactToAdd);
	// 	return personDao.save(personToAddContact);
	// }

	// public ContactInformation updateContactInformation(int personId, int contactId, ContactInformation contactinformation) {
	// 	Person getPersonContact = personDao.findById(personId).get();
		
	// 	// List<ContactInformation> contact = getPersonContact.getContactInformation();

	// 	return contactDetails;

	// }

	public Person addPersonRole(Roles role, int id) {
		Optional<Person> person = personDao.findById(id);		
		List<Roles> role2 = person.get().getPersonRole();
		role2.add(role);
		Person personToAddRole = person.get();
		personToAddRole.setRole(role2);
		return personDao.save(personToAddRole);
	}

	public void deletePersonRole(int personId, int roleId) {
		Optional<Person> person = personDao.findById(personId);
		List<Roles> role = person.get().getPersonRole();
		Iterator<Roles> iterate = role.iterator();
		while(iterate.hasNext()) {
			Roles deleteRole = iterate.next();
			if(deleteRole.getId() == roleId) {
				iterate.remove();
			}
		}

		Person updatePerson = person.get();
		updatePerson.setRole(role);
		personDao.save(updatePerson);
	}

	public void deleteConstraint(Roles role) {
		List<Person> person = personDao.findAll();
		person.stream().forEach(person2 -> {
			Iterator<Roles> iterate = person2.getPersonRole().iterator();
			while(iterate.hasNext()) {
				Roles deleteRole = iterate.next();
				if(deleteRole.getId() == role.getId()) {
					iterate.remove();
				}

				personDao.save(person2);
			}
		});
	}

	public List<Person> getPersonsByDateHired() {
		Optional<List<Person>> personByDateHired = personDao.getPersonOrderByDateHired();
		List<Person> p = personByDateHired.get();
		return p;
	}

	public List<Person> getPersonsByLastName() {
		Optional<List<Person>> personByLastName = personDao.getPersonOrderByLastName();
		List<Person> p = personByLastName.get();
		return p;
	}

	public List<Person> getPersonsByGwa() {
		List<Person> person = personDao.findAll();
		Collections.sort(person, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				return Double.compare(p1.getGwa(), p2.getGwa());
			}
		});

		return person;
	}

	// public void updateContact(int personId, int contactId, ContactInformation contactinformation) {
	// 	Optional<Person> personContactToEdit = personDao.findById(personId);
	// 	List<ContactInformation> contactInformationToEdit = personContactToEdit.getContactInformation();
	// 	contactInformationToEdit.setMobileNumber(contactinformation.getMobileNumber());
	// 	contactInformationToEdit.setLandline(contactinformation.getLandline());
	// 	contactInformationToEdit.setEmail(contactinformation.getEmail());
	// 	personContactToEdit.setContactInformation(contactInformationToEdit);
	// 	personDao.save(personContactToEdit);

	// }


}