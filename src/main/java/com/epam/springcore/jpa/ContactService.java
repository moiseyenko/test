package com.epam.springcore.jpa;

import java.util.List;

public interface ContactService {
	List<Contact> findAll();
	List<Contact> findAllWithDetail();
	Contact findById(int id);
	Contact save(Contact contact);
	void delete(Contact contact);
}
