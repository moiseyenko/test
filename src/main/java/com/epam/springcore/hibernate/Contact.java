package com.epam.springcore.hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
@Table(name="contact")
public class Contact implements Serializable{
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int version;
	private Set<ContactTelDetail> contactTelDetails = new HashSet<>();
	private Set<Hobby> hobbies = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="BIRTH_DATE")
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Version
	@Column(name="VERSION")
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<ContactTelDetail> getContactTelDetails() {
		return contactTelDetails;
	}
	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		this.contactTelDetails = contactTelDetails;
	}
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}
	
	@ManyToMany
	@JoinTable(name="contact_hobby_detail",
				joinColumns=@JoinColumn(name="CONTACT_ID"),
				inverseJoinColumns=@JoinColumn(name="HOBBY_ID"))
	public Set<Hobby> getHobbies() {
		return hobbies;
	}
	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", version=" + version + ", contactTelDetails=" + contactTelDetails + "]";
	}

}
