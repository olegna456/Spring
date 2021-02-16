package com.exist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
@Entity
@Table(name="roles")
public class Roles implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="role")
	private String role;

	@ManyToMany(mappedBy="personRole", cascade=CascadeType.ALL)
	private List<Person> pRole = new ArrayList<>();


	public Roles() {

	}

	public Roles(String role) {
		this.role = role;
	}

	public void setRoleId(int id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return this.id;
	}

	public String getRole() {
		return this.role;
	}

	private void setPersonRole(List<Person> pRole) {
		this.pRole = pRole;
	}

	public List<Person> getPersonRole() {
		return this.pRole;
	}

	public String toString() {
		return "[Id: " + id + "\n" +
				"Role: " + role + "]";
	}
}