package com.exist.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="contactinformation")
public class ContactInformation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="mobile_number")
	private String mobileNumber;

	@Column(name="landline")
	private String landline;

	@Column(name="email")
	private String email;

	@Transient
	private Person person;

	public ContactInformation() {

	}

	public ContactInformation(String mobileNumber, String landline, String  email) {
		this.mobileNumber = mobileNumber;
		this.landline = landline;
		this.email = email;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public String getLandline() {
		return this.landline;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return this.person;
	}

	public String toString() {
		return "[Id: " + id + "\n" +
				"Mobile number: " + mobileNumber + "\n" +
				"landline: " + landline + "\n" +
				"Email: " + email + "]";
	}


}