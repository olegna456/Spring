package com.exist.service;
import com.exist.dao.PersonDao;
import com.exist.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@ComponentScan(basePackages = {"com.exist.dao"})
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
		List<Person> person = personDao.findAll();
		List<Person> list  = setRoleToNull(person);
		return list;
	}

	public List<Person> setRoleToNull(List<Person> list) {
		list.stream()
			.forEach(person -> {
				person.getPersonRole().stream()
					.forEach(set -> {						
						set.setPersonRole(null);
					});
			});
		return list;
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
			personByOrderToReturn = setRoleToNull(personByOrder.get());
		}else if(choice == 2) {
			personByOrder = personDao.getPersonOrderByLastName();
			personByOrderToReturn = setRoleToNull(personByOrder.get());
		} else if(choice == 3) {
			personByOrderToReturn =	getPersonsByGwa();
			personByOrderToReturn = setRoleToNull(personByOrderToReturn);
		} else {
			return null;
		}
		return personByOrderToReturn;
	}

	public void deletePersonById(int id) {
		personDao.deleteById(id);
	}

	public List<Person> getPersonsByDateHired() {
		Optional<List<Person>> personByDateHired = personDao.getPersonOrderByDateHired();
		List<Person> personOrderByDateHired = personByDateHired.get();
		return personOrderByDateHired;
	}

	public List<Person> getPersonsByLastName() {
		Optional<List<Person>> personByLastName = personDao.getPersonOrderByLastName();
		List<Person> personOrderByLastName = personByLastName.get();
		return personOrderByLastName;
	}

	public List<Person> getPersonsByGwa() {
		List<Person> personOrderByGwa = personDao.findAll();
		Collections.sort(personOrderByGwa, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				return Double.compare(p1.getGwa(), p2.getGwa());
			}
		});
		return personOrderByGwa;
	}
}




// public void deleteConstraint(Roles role) {
	// 	List<Person> person = personDao.findAll();
	// 	person.stream().forEach(person2 -> {
	// 		Iterator<Roles> iterate = person2.getPersonRole().iterator();
	// 		while(iterate.hasNext()) {
	// 			Roles deleteRole = iterate.next();
	// 			if(deleteRole.getId() == role.getId()) {
	// 				iterate.remove();
	// 			}

	// 			personDao.save(person2);
	// 		}
	// 	});
	// }