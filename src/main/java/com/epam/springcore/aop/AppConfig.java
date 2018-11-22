package com.epam.springcore.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan("com.epam.springcore.aop")
public class AppConfig {

	@Bean
	public TestTarget fooService() {
		return new TestTarget();
	}

	/*@Bean
	public LoggingAspect myAspect() {
		return new LoggingAspect();
	}*/
}
