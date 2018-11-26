package com.epam.springcore.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Log4j2
@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		log.info("\nin findAll\n");
		return entityManager.createNamedQuery("Contact.findAll", Contact.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAllWithDetail() {
		//return sessionFactory.getCurrentSession().getNamedQuery("Contact.findAllWithDetail").list();
		return null;
	}

	@Override
	public Contact findById(int id) {
		//return (Contact) sessionFactory.getCurrentSession().getNamedQuery("Contact.findById").setParameter("id", id).uniqueResult();
		return null;
	}

	@Override
	public Contact save(Contact contact) {
		/*sessionFactory.getCurrentSession().saveOrUpdate(contact);
		log.info("Contact saved with id: {}\n", contact.getId());
		return contact;*/
		return null;
	}

	@Override
	public void delete(Contact contact) {
		/*sessionFactory.getCurrentSession().delete(contact);
		log.info("Contact deleted with id: {}", contact.getId());*/
	}
	
}
