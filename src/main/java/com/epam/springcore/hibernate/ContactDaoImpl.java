package com.epam.springcore.hibernate;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository("contactDaoImpl")
@Transactional
public class ContactDaoImpl implements ContactDao{
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory") 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
	}

	@Override
	public List<Contact> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact save(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Contact contact) {
		// TODO Auto-generated method stub
		
	}
	
}
