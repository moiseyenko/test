package com.epam.springcore;


public class App{
	
	private Client client;
	private EventLogger eventLogger;
	
	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}
	
    public static void main( String[] args ) {
    	Client client = new Client("1", "John Smith");
    	EventLogger eventLogger = new ConsoleEventLogger();
    	App app = new App(client, eventLogger);
    	app.logEvent("Some event for uset 1");
    
    }
    
    private void logEvent(String msg) {
    	String message = msg.replaceAll(client.getId(), client.getFullName());
    	eventLogger.logEvent(message);
    }
}
