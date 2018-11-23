package com.epam.springcore.aop;

public class TestTarget {
	public void uncheckedError() {
		throw new IllegalArgumentException("Bar");
	}
}
