package com.epam.springcore.hibernate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDao {
	
	private static final String ALL_CONTACTS = "select * from contact";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Contact> findAllContacts (){
		return jdbcTemplate.query(ALL_CONTACTS, new RowMapper<Contact>() {
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setFirstName(rs.getString("first_name"));
				contact.setLastName(rs.getString("last_name"));
				contact.setBirthDate(rs.getDate("birth_date").toLocalDate());
				contact.setVersion(rs.getInt("version"));
				return contact;
			}	
		});
	}
	
	
}
