package com.epam.springcore.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryImpl implements ContactSummeryService{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly=true)
	public List<ContactSummary> findAll() {
		return entityManager.createQuery("select new com.epam.springcore.jpa.ContactSummary(c.firstName, c.lastName, t.telNumber) "
				+ "from Contact c left join c.contactTelDetails t "
				+ "where t.telType = 'Home'", 
				ContactSummary.class).getResultList();
	}

}
