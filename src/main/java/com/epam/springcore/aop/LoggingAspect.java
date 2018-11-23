package com.epam.springcore.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect{
//execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.*(..))
	private static Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "within(com.epam.springcore..*)", throwing="ex" )
	public void logException(JoinPoint joinPoint, IllegalArgumentException ex){
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    LOG.info("Write something in the log... We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + ex.getMessage(), ex);
	}
}
