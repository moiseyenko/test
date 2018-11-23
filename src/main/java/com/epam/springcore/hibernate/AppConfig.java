package com.epam.springcore.hibernate;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:hibernate.properties"})
@ComponentScan("com.epam.springcore.hibernate")
public class AppConfig {
	
	@Autowired
	private Environment env;

	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource () {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(false)
				.setName("testbd")
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("schema.sql", "test-data.sql")
				.setScriptEncoding("UTF-8")
				.build();
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager TransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager= new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.epam.springcore.hibernate"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	
	@SuppressWarnings("serial")
	private Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.max_fetch_depth",env.getProperty("hibernate.max_fetch_depth"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.jdbc.fetch_size", env.getProperty("hibernate.jdbc.fetch_size"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.jdbc.batch_size"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.show_sql"));
			}
			
		};
	}
	
}