package com.epam.springcore.hibernate;

import java.util.List;

public interface ContactDao {
	List<Contact> findAll();
	List<Contact> findAllWithDetail();
	Contact findById();
	Contact save(Contact contact);
	void delete(Contact contact);
}
