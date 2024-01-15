package com.mmd.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(13)
public class ApiAnalyticsAspect {

	@Before("com.mmd.aopdemo.aspect.AopPointcutDeclarations.forDaoPackageExceptGettersSetters()") //Re-use the pointcut expression as many times as you need.
	public void apiAnalytics(){
		System.out.println("Performing api analytics");
		System.out.println();
	}
}
