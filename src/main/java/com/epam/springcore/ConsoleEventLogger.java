package com.epam.springcore;

public class ConsoleEventLogger implements EventLogger{

	@Override
	public void logEvent(String msg) {
		System.out.println(msg);
	}
	
}
