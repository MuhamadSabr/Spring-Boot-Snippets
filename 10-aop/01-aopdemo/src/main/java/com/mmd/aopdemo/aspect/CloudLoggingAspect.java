package com.mmd.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(8)
public class CloudLoggingAspect {

	@Before("com.mmd.aopdemo.aspect.AopPointcutDeclarations.forDaoPackageExceptGettersSetters()") //Re-use the pointcut expression as many times as you need.
	public void logToCloud(){
		System.out.println("Performing Cloud logging in an Async fashion");
		System.out.println();
	}
}
