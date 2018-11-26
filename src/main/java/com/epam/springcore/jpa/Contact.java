package com.epam.springcore.jpa;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString(exclude= {"contactTelDetails","hobbies" })
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
@Entity
@Table(name="contact")
@NamedQueries(value = { @NamedQuery(name = "Contact.findAll", query = "select c from Contact c"),
		
						@NamedQuery(name = "Contact.findAllWithDetail", 
									query = "select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h"),
						@NamedQuery(name = "Contact.findById", 
									query = "select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h "
											+ "where c.id = :id")})
public class Contact implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="last_NAME")
	private String lastName;
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	@Version
	@Column(name="VERSION")
	private int version;
	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<ContactTelDetail> contactTelDetails = new HashSet<>();
	@ManyToMany
	@JoinTable(name="contact_hobby_detail",
				joinColumns=@JoinColumn(name="CONTACT_ID"),
				inverseJoinColumns=@JoinColumn(name="HOBBY_ID"))
	private Set<Hobby> hobbies = new HashSet<>();
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}
	

}
