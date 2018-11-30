package com.epam.springcore.jpa;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Log4j2
@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactServiceUntype {

	final static String NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from contact";

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	@Transactional
	public void displayAllContactSummary() {
		List result = entityManager.createQuery("select c.firstName, c.lastName, t.telNumber "
				+ "from Contact c left join c.contactTelDetails t " + "where t.telType='Home'").getResultList();
		int count = 0;
		for (Iterator i = result.iterator(); i.hasNext();) {
			Object[] values = (Object[]) i.next();
			System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		log.info("\nin findAll\n");
		return entityManager.createNamedQuery("Contact.findAll", Contact.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAllWithDetail() {
		return entityManager.createNamedQuery("Contact.findAllWithDetail", Contact.class).getResultList();

	}

	@Override
	public Contact findById(int id) {
		return entityManager.createNamedQuery("Contact.findById", Contact.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public Contact save(Contact contact) {
		if (contact.getId() == null) {
			log.info("Inserting new contact");
			entityManager.persist(contact);
		} else {
			entityManager.merge(contact);
			log.info("Updating existing contact");
		}
		log.info("Contact saved with id: {}\n", contact.getId());
		return contact;
	}

	@Override
	public void delete(Contact contact) {
		Contact mergedContact = entityManager.merge(contact);
		entityManager.remove(mergedContact);
		log.info("Contact deleted with id: {}", contact.getId());

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAllByNativeQuery() {
		return entityManager.createNativeQuery(NATIVE_QUERY, "contactResult").getResultList();
	}

	@Transactional
	@Override
	public List<Contact> fingByCriteriaQuery(String firstName, String lastName) {
		log.info("Finding contact for firstName:  " + firstName + " and lastName: " + lastName+ "\n");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
		Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
		contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
		contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
		criteriaQuery.select(contactRoot).distinct(true);
		Predicate criteria = criteriaBuilder.conjunction();
		if (firstName != null) {
			Predicate р = criteriaBuilder.equal(contactRoot.get(Contact_.firstName), firstName);
			criteria = criteriaBuilder.and(criteria, р);
		}
		if (lastName != null) {
			Predicate р = criteriaBuilder.equal(contactRoot.get(Contact_.lastName), lastName);
			criteria = criteriaBuilder.and(criteria, р);
		}
		criteriaQuery.where(criteria);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
