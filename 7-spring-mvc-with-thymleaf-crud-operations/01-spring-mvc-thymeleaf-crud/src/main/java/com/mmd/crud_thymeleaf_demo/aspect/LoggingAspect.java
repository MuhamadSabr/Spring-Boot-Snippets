package com.mmd.crud_thymeleaf_demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

	//Setup logger named logger.
	Logger logger = Logger.getLogger(getClass().getSimpleName());

	//Declare pointcut expressions for every method in the packages you want, then combine them into one Pointcut expression
	@Pointcut("execution (* com.mmd.crud_thymeleaf_demo.controller.*.*(..))")
	private void controllerPointcut() {}

	@Pointcut("execution (* com.mmd.crud_thymeleaf_demo.repository.*.*(..))")
	private void repositoryPointcut() {}

	@Pointcut("execution (* com.mmd.crud_thymeleaf_demo.service.*.*(..))")
	private void servicePointcut() {}

	@Pointcut(" controllerPointcut() || repositoryPointcut() || servicePointcut()")
	private void targetPackagesPointcut() {}


	//Add your before advice method.
	@Before("targetPackagesPointcut()")
	public void beforeAdvice(JoinPoint joinPoint){

		//Log methods called
		String methodCalled = joinPoint.getSignature().toLongString();
		logger.info(methodCalled);

		//Add each argument object to the logger
		Arrays.asList(joinPoint.getArgs()).forEach(obj -> logger.info("Arg: " + obj));
	}

	@AfterReturning(pointcut = "targetPackagesPointcut()", returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, Object result){

		//Log returning method name
		String returningMethod = "Returning from method: " + joinPoint.getSignature().getName();
		logger.info(returningMethod);

		//Log returned data.
		logger.info("Data returned: " + result);
	}
}
