package com.epam.springcore.hibernate;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository("contactDaoImpl")
@Transactional
public class ContactDaoImpl implements ContactDao{
	
	@Resource(name="sessionFactory") 
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		log.info("\nin findAll\n");
		return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAllWithDetail() {
		return sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
	}

	@Override
	public Contact findById(int id) {
		return (Contact) sessionFactory.getCurrentSession().getNamedQuery("Contact.findById").setParameter("id", id).uniqueResult();
	}

	@Override
	public Contact save(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		log.info("Contact saved with id: {}\n", contact.getId());
		return contact;
	}

	@Override
	public void delete(Contact contact) {
		sessionFactory.getCurrentSession().delete(contact);
		log.info("Contact deleted with id: {}", contact.getId());
	}
	
}
