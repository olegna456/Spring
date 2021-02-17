package com.exist.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="person")
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	
	@Column(name="last_name")
	private String lastName;

	@Column(name="first_name")
	private String firstName;


	@Column(name="middle_name")
	private String middleName;

	@Column(name="suffix")
	private String suffix;

	@Column(name="title")
	private String title;

	@Column(name="street_number")
	private int streetNumber;

	@Column(name="barangay" )
	private String barangay;

	@Column(name="municipality_city")
	private String municipalityOrCity;

	@Column(name="zipcode")
	private int zipcode;

	@Column(name="birthday")
	private Date birthday;

	@Column(name="date_hired")
	private Date dateHired;

	@Column(name="grade_weighted_average")
	private double gwa;

	@Column(name="Currently_employed")
	private boolean employed;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="person_id",referencedColumnName="id")
	private List<ContactInformation> contactinformation = new ArrayList<>();


	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(name="person_role", joinColumns = {@JoinColumn(name="person_id")}, inverseJoinColumns={@JoinColumn(name="role_id")})
	private List<Roles> personRole = new ArrayList<>();


	public Person() { }

	public Person(String firstName, String lastName, String middleName, String suffix, String title, int streetNumber, String barangay, String municipalityOrCity, int zipcode, Date birthday, Date dateHired, double gwa, boolean employed) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.suffix = suffix;
		this.title = title;
		this.streetNumber = streetNumber;
		this.barangay = barangay;
		this.municipalityOrCity = municipalityOrCity;
		this.zipcode = zipcode;
		this.birthday = birthday;
		this.dateHired = dateHired;
		this.gwa = gwa;
		this.employed = employed;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
 
	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public void setMunicipalityOrCity(String municipalityOrCity) {
		this.municipalityOrCity = municipalityOrCity;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	public void setGwa(double gwa) {
		this.gwa = gwa;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}


	public int getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public String getTitle() {
		return this.title;
	}

	public int getStreetNumber() {
		return this.streetNumber;
	}

	public String getBarangay() {
		return this.barangay;
	}

	public String getMunicipalityOrCity() {
		return this.municipalityOrCity;
	}

	public int getZipcode() {
		return this.zipcode;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public Date getDateHired() {
		return this.dateHired;
	}

	public double getGwa() {
		return this.gwa;
	}

	public boolean getEmployed() {
		return this.employed;
	}

	public void setContactInformation(List<ContactInformation> contactinformation) {
		this.contactinformation = contactinformation;
	}

	public List<ContactInformation> getContactInformation() {
		return this.contactinformation;
	}

	public void setRole(List<Roles> personRole) {
		this.personRole = personRole;
	}

	public List<Roles> getPersonRole() {
		return this.personRole;
	}

	public String toString() {
		return "[Id: " + id + "\n" +
				"Firstname: " + firstName + "\n" + 
				"Lastname: " + lastName + "]";
 	}

}