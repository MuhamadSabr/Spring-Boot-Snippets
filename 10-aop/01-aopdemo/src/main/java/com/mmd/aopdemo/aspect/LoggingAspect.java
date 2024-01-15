package com.mmd.aopdemo.aspect;

import com.mmd.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component //Make it a spring managed bean. By combining these two annotations, the class is recognized as a spring bean and can participate in AOP operations
@Order(5)
public class LoggingAspect {

	//Around advice
	@Around(value = "execution(* com.mmd.aopdemo.service.TrafficFortuneService.getFortune(..))")
	public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		System.out.println("Advising on: " + proceedingJoinPoint.getTarget().getClass().getSimpleName() + ", method: " + proceedingJoinPoint.getSignature().getName());
		Object obj = null;
		try {
			obj = proceedingJoinPoint.proceed(); // U get a handle of the method by using ProceedingJoinPoint, u execute the method by using .proceed() on it.
		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			System.out.println("I will pass it back to  u Main.app, u know what to do with it");
			throw e;D
		}
		System.out.println("Time of executing the target method: " + (System.currentTimeMillis()-start) );
		return obj;
	}

	//Add after finally advice
	@After(value = "execution(* com.mmd.aopdemo.dao.AccountDAO.findAccounts())")
	public void afterAdvice(JoinPoint joinPoint){
		System.out.println("Logging in @After finally advice");
	}

	//Add advice method for after throwing case
	@AfterThrowing(value = "execution(* com.mmd.aopdemo.dao.AccountDAO.findAccounts())", throwing = "exc")//All attributes are the same as AfterReturning except names
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable exc){
		System.out.println("Logging exception: " + exc.getMessage() + ", in class" + joinPoint.getTarget().getClass().getSimpleName() + ", method: " +
				joinPoint.getSignature().getName());
	}

	//Add advice method for after returning successfully.
	@AfterReturning(
			pointcut = "execution(* com.mmd.aopdemo.dao.AccountDAO.findAccounts())",
			returning = "resultList")
	public void afterReturningAdvice(JoinPoint joinPoint, List<Account> resultList){

		//Point out which method we are advising on			same as .getThis()
		System.out.println("Logging on target class: " + joinPoint.getTarget() + ",type of aop operation " + joinPoint.getKind());
		System.out.println(joinPoint.getClass().getSimpleName());//Output: MethodInvocationProceedingJoinPoint
		System.out.println(joinPoint.getStaticPart());
		System.out.println("Logging successful execution and return of value");
		System.out.println("Logging also return value of: " + resultList);

		//Intercepting the result and modify it. The modified version will be sent back to the caller.
		resultList.get(0).setAccount("Send 5000 if u want ur account back");
		resultList.get(0).setName("Hacked");
	}

	@Before("com.mmd.aopdemo.aspect.AopPointcutDeclarations.forDaoPackageExceptGettersSetters()")//The pointcut expression to specify where the advice should be applied.
	public void beforeAdvice(JoinPoint joinPoint){
		System.out.println();
		System.out.println("Executing @Before advice for logging purposes");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		System.out.println("Logging method: " + methodSignature);
		Object[] args = joinPoint.getArgs();
		Account account = null;
		boolean b = false;
		try {
			account = (Account) args[0];
			b = (boolean) args[1];
		}catch (IndexOutOfBoundsException ex){
			System.out.println("One arg passed");
		}
		System.out.println("Adding account with details: " + account);
		System.out.println("Related condition: " + b);
		System.out.println();
	}
}
