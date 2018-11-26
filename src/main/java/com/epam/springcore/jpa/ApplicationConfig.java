package com.epam.springcore.jpa;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:hibernate.properties"})
@ComponentScan("com.epam.springcore.jpa")
public class ApplicationConfig {
	
	@Autowired
	private Environment env;

	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource () {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(false)
				.setName("testbd")
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("sql/V1.1__schema.sql", "sql/V1.2__test-data.sql")
				.setScriptEncoding("UTF-8")
				.build();
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager= new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
		
	}
	
	@Bean(name="entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan(new String[] {"com.epam.springcore.jpa"});
		entityManagerFactory.setJpaProperties(jpaProperties());
		return entityManagerFactory;
	}
	
	
	@SuppressWarnings("serial")
	private Properties jpaProperties() {
		return new Properties() {
			{
				setProperty("hibernate.max_fetch_depth",env.getProperty("hibernate.max_fetch_depth"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.jdbc.fetch_size", env.getProperty("hibernate.jdbc.fetch_size"));
				setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
			}
			
		};
	}
	
}
