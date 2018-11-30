package com.epam.springcore.jpa;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@SuppressWarnings("serial")
public class ContactSummary implements Serializable{
	private String firstName;
	private String lastName;
	private String homeTelNumber;
}
