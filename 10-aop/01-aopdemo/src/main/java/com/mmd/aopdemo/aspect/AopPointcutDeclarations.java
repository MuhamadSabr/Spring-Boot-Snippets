package com.mmd.aopdemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AopPointcutDeclarations {

	//Declare a private Pointcut expression
	@Pointcut("execution(* com.mmd.aopdemo.dao.*.*(..))")
	public void forDaoPackage(){}

	@Pointcut("execution(* com.mmd.aopdemo.dao.*.get*())")
	public void getters() {}

	@Pointcut("execution(* com.mmd.aopdemo.dao.*.set*(*))")
	public void setters() {}

	@Pointcut("getters() || setters()")
	public void gettersSetters() {}

	@Pointcut("forDaoPackage() && !gettersSetters()")
	public void forDaoPackageExceptGettersSetters() {}

}
