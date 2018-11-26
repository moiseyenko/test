package com.epam.springcore.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="hobby")
public class Hobby implements Serializable{
	private String hobbyId;
	private Set<Contact> contacts = new HashSet<>();

	@Id
	@Column(name="HOBBY_ID")
	public String getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(String hobbyId) {
		this.hobbyId = hobbyId;
	}

	@ManyToMany
	@JoinTable(name="contact_hobby_detail",
				joinColumns=@JoinColumn(name="HOBBY_ID"),
				inverseJoinColumns=@JoinColumn(name="CONTACT_ID"))
	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "Hobby [hobbyId=" + hobbyId + "]";
	}

		
}
