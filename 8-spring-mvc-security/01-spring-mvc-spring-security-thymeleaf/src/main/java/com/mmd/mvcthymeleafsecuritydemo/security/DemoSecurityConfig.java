package com.mmd.mvcthymeleafsecuritydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

		httpSecurity.authorizeHttpRequests(configurer-> configurer
						.requestMatchers("/").hasRole("EMPLOYEE")
						.requestMatchers("/leaders/**").hasRole("MANAGER")
						.requestMatchers("/systemAdmins/**").hasRole("ADMIN")
						.anyRequest()//Any incoming request to the app
						.authenticated() //should be authenticated(ie. logged in)
				)
				.formLogin(form-> form.loginPage("/showLoginPage") //A controller method u define and map to this, handles showing the login page you want.
						.loginProcessingUrl("/authenticateTheUser") //Your login form must send data to this address, then Spring will handle the rest of authentication
						.permitAll()		//The public can see the login page.
				)
				.logout(LogoutConfigurer::permitAll)//Adds the default /logout
				.exceptionHandling(
						configurer-> configurer.accessDeniedPage("/accessDenied")//Create /access-denied controller method to show the page
				)
		;



		return httpSecurity.build(); //Returns DefaultSecurityFilterChain.
	}

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource){//Inject the datasource that is automatically created by Spring Boot

		//Tell Spring Security to user JDBC authentication with our datasource.
		return new JdbcUserDetailsManager(dataSource); //Implements UserDetailsManger
	}

//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//		UserDetails john = User.builder()
//				.username("john")
//				.password("{noop}test123")
//				.roles("EMPLOYEE")
//				.accountExpired(false)
//				.credentialsExpired(false)
//				.accountLocked(false)
//				.disabled(false)
//				.build();//build(); returns UserDetails.
//
//		UserDetails mary = User.builder()
//				.username("mary")
//				.password("{noop}test123")
//				.roles("EMPLOYEE", "MANAGER")
//				.accountExpired(false)
//				.credentialsExpired(false)
//				.accountLocked(false)
//				.disabled(false)
//				.build();
//
//		UserDetails mmd = User.builder()
//				.username("mmd")
//				.password("{noop}test123")
//				.roles("EMPLOYEE", "MANAGER", "ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(john, mary, mmd);
//	}

}
