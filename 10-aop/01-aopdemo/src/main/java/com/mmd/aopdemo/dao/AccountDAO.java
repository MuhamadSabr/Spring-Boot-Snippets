package com.mmd.aopdemo.dao;

import com.mmd.aopdemo.Account;

import java.util.List;

public interface AccountDAO {
	void addAccount(Account account, boolean b);

	boolean dooer();

	String getName();

	void setName(String name);

	String getServiceName();

	void setServiceName(String serviceName);

	List<Account> findAccounts();
}
