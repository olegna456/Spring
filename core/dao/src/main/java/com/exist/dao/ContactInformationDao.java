package com.exist.dao;
import com.exist.model.ContactInformation;
import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ContactInformationDao extends JpaRepository<ContactInformation, Integer> {
	

	// @Query(value = "SELECT * FROM contactinformation WHERE person_id=?1", nativeQuery = true)
	// List<ContactInformation> getDetails(int personId);
}