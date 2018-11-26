package com.epam.springcore.hibernate;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ContactDao contactDao = (ContactDao) ctx.getBean("contactDaoImpl");
		List<Contact> list = contactDao.findAll();
		System.out.println(list);
		ctx.close();
	}
	
	
}
