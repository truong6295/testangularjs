package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the Personal database table.
 * 
 */
@Entity
@NamedQuery(name="Personal.findAll", query="SELECT p FROM Personal p")
@Proxy(lazy=false)
public class Personal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int personalId;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String gender;

	private String name;

	//bi-directional many-to-one association to Assignment
	@OneToMany(mappedBy="personal", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Assignment> assignments;

	public Personal() {
	}
	public Personal(String address,Date birthday,String gender,String name) {
		this.address=address;
		this.birthday=birthday;
		this.gender=gender;
		this.name=name;
	}
	public int getPersonalId() {
		return this.personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Assignment addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
		assignment.setPersonal(this);

		return assignment;
	}

	public Assignment removeAssignment(Assignment assignment) {
		getAssignments().remove(assignment);
		assignment.setPersonal(null);

		return assignment;
	}

}