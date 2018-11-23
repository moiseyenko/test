package com.epam.springcore.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ContactDao contactDao = (ContactDao) ctx.getBean("contactDaoImpl");
		System.out.println(contactDao.findAll());
		ctx.close();
	}
	
	
}
