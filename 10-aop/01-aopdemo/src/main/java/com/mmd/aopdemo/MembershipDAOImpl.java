package com.mmd.aopdemo;

import com.mmd.aopdemo.Account;
import com.mmd.aopdemo.MembershipDAO;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

	public int addSillyMember(){
		System.out.println(getClass().getSimpleName() + ": Adding member code");
		return 0;
	}

	@Override
	public Account remove(String hi) {
		System.out.println(getClass().getSimpleName() + ": " + hi);
		return null;
	}
}
