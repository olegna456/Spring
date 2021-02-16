package com.exist.dao;
import com.exist.model.Person;
import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {

	// @Query("select p FROM person ORDER BY p.date_hired")
	

	@Query(value = "SELECT * FROM person ORDER BY date_hired ASC", nativeQuery = true)
	Optional<List<Person>> getPersonOrderByDateHired();

	@Query(value = "SELECT * FROM person ORDER BY last_name ASC", nativeQuery = true)
	Optional<List<Person>> getPersonOrderByLastName();
}	