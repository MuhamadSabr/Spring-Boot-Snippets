package com.mmd.aopdemo;

import com.mmd.aopdemo.dao.AccountDAO;
import com.mmd.aopdemo.dao.AccountDAOImpl;
import com.mmd.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService){

		return runner->{

//			theBeforeAdvice(accountDAO, membershipDAO);
//			theAfterReturningAdvice(accountDAO);
//			theAfterThrowingAdvice(accountDAO);
//			theAfterAdvice(accountDAO);
			theAroundAdvice(trafficFortuneService);

		};
	}

	private void theAroundAdvice(TrafficFortuneService trafficFortuneService) {

		System.out.println("Calling getFortune");
		try {
			System.out.println("My fortune is: " + trafficFortuneService.getFortune());
		}catch (Exception e){
			System.out.println("Main got it, here it is: " + e.getMessage());
		}
		System.out.println("Finished");
	}

	private void theAfterAdvice(AccountDAO accountDao) {
		List<Account> accounts = null;
		try{
			accounts = accountDao.findAccounts();
		}catch (Exception exp){
			System.out.println("Caught exception: " + exp.getMessage() + ", in Main App");
			return;
		}
		System.out.println("Accounts: " + accounts);
	}

	private void theAfterThrowingAdvice(AccountDAO accountDao) {

		List<Account> accounts = null;
		try{
			accounts = accountDao.findAccounts();
		}catch (Exception exp){
			System.out.println("Caught exception: " + exp.getMessage() + ", in Main App");
			return;
		}
		System.out.println("Accounts: " + accounts);
	}

	private void theAfterReturningAdvice(AccountDAO accountDao) {

		//Call method to find accounts
		List<Account> accounts = accountDao.findAccounts();
		System.out.println("Accounts: " + accounts);
	}

	public static void theBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO){

		//Call the business method. Behind the scenes, the LoggingAspect listens to execute its code.
		accountDAO.addAccount(new Account("13454353450345", "Mmd Service 3145"), true);

		accountDAO.dooer();

		membershipDAO.addSillyMember();
		membershipDAO.remove("Hello");

		//U cannot call any method of the implementation class, using the DAO interface if it is not declared int the interface
		AccountDAOImpl account = new AccountDAOImpl();
		accountDAO.setName("Mohammed");
		accountDAO.setServiceName("Mohammed Service");
		System.out.println("Get name: " + accountDAO.getName());
		System.out.println("Get serviceName: " + accountDAO.getServiceName());
	}
}
