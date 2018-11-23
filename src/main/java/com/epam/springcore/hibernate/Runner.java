package com.epam.springcore.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ContactDao contactDao = ctx.getBean(ContactDao.class);
		System.out.println(contactDao.findAllContacts());
		ctx.close();
	}
	
	
}
