package com.epam.springcore.jpa;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ContactServiceUntype contactDao = (ContactServiceUntype) ctx.getBean("jpaContactService");
		//List<Contact> list = contactDao.findAllWithDetail();

		/*ContactSummeryService contactSummeryService = (ContactSummeryService) ctx.getBean("contactSummaryService");
		List<ContactSummary> contacts = contactSummeryService.findAll();

		for (ContactSummary item : contacts) {
			System.out.println(item);
		}*/

		List<Contact> list = contactDao.fingByCriteriaQuery("Scott", "Tiger");
		listContactsWithDetail(list);

		/*
		 * for (Contact item : list) { System.out.println(item); if
		 * (item.getContactTelDetails() != null) { for (ContactTelDetail detail :
		 * item.getContactTelDetails()) { System.out.println(detail); } } if
		 * (item.getHobbies() != null) { for (Hobby hobby : item.getHobbies()) {
		 * System.out.println(hobby); } } System.out.println(); }
		 */

		/*
		 * Contact contact = contactDao.findById(2); System.out.println(contact); if
		 * (contact.getContactTelDetails() != null) { for (ContactTelDetail detail :
		 * contact.getContactTelDetails()) { System.out.println(detail); } } if
		 * (contact.getHobbies() != null) { for (Hobby hobby : contact.getHobbies()) {
		 * System.out.println(hobby); } }
		 */

		/*
		 * contactDao.displayAllContactSummary();
		 * 
		 * Contact contact = new Contact(); contact.setFirstName("Michael");
		 * contact.setLastName("Jackson"); contact.setBirthDate(new Date());
		 * ContactTelDetail contactTelDetail = new ContactTelDetail("Home",
		 * "1111111111"); contact.addContactTelDetail(contactTelDetail);
		 * contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
		 * contact.addContactTelDetail(contactTelDetail); contactDao.save(contact);
		 * listContactsWithDetail(contactDao.findAllWithDetail());
		 * contact.setFirstName("ALEX"); contactDao.save(contact);
		 */

		/*
		 * listContactsWithDetail(contactDao.findAllWithDetail()); Contact contact =
		 * contactDao.findById(2); contact.setFirstName("Kim Fung");
		 * Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		 * ContactTelDetail toDeleteContactTel = null; for (ContactTelDetail contactTel
		 * : contactTels) { if (contactTel.getTelType().equals("Home")) {
		 * toDeleteContactTel = contactTel; } }
		 * contact.removeContactTelDetail(toDeleteContactTel); contactDao.save(contact);
		 * 
		 * listContactsWithDetail(contactDao.findAllWithDetail());
		 */

		/*
		 * Contact contact = contactDao.findById(2); contactDao.delete(contact);
		 * listContactsWithDetail(contactDao.findAllWithDetail());
		 * 
		 * ctx.close();
		 */
	}

	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts with details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}
			if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies())
					System.out.println(hobby);
			}
		}
		System.out.println();

	}

}
