package com.epam.springcore.hibernate;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@PropertySource({"classpath:h2.properties"})
@ComponentScan("com.epam.springcore.hibernate")
public class AppConfig {
	
	@Autowired
	private Environment env;

	
	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource () throws IOException {
		return new EmbeddedDatabaseBuilder()
				.generateUniqueName(false)
				.setName("testbd")
				.setType(EmbeddedDatabaseType.H2)
				.addScripts("schema.sql", "test-data.sql")
				.setScriptEncoding("UTF-8")
				.build();
	}
	@Bean
	public JdbcTemplate jdbcTemplate() throws IOException {
		return new JdbcTemplate(dataSource());
	}
	
}
