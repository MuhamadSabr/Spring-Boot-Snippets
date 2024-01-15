package com.mmd.aopdemo.dao;

import com.mmd.aopdemo.Account;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //sub annotation of @Component, makes this class a java bean and a candidate for DI.
public class AccountDAOImpl implements AccountDAO{

	private String name;
	private String serviceName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName is called with parameter: " + name);
		this.name = name;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		System.out.println("SetServiceName is called with parameter: " + serviceName);
		this.serviceName = serviceName;
	}


	@Override
	public void addAccount(Account account, boolean b) {
		System.out.println(getClass().getSimpleName() + ": doing DB work of adding an account");
	}

	@Override
	public boolean dooer() {
		System.out.println(getClass().getSimpleName() + ": dooer");
		return true;
	}

	@Override
	public List<Account> findAccounts() {

		boolean tripWire = false;
		if(tripWire){
			throw new RuntimeException("You tripped");
		}

		return List.of(
				new Account("12345", "mmd12345"),
				new Account("67890", "ah67890"),
				new Account("11121", "kar11121"),
				new Account("31415", "jaw31415")
		);
	}
}
