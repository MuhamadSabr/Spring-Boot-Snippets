package com.mmd.crudrestdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration	//user, pass information in the .properteis will no longer be read after defining ur own config class.
//This annotation marks the class as a source of configuration, bean definitions, custom properties n other settings that need to be managed by the Spring Container.
public class DemoSecurityConfig {

//	@Bean	//Injecting DataSource(auto-configured by Spring) is all the configuration u need to make use of Spring Security default schema, regarding Java code.
//	public UserDetailsManager userDetailsManager(DataSource dataSource){		//(U needed to create the matching tables and add ur users, and their roles)
//
//		return new JdbcUserDetailsManager(dataSource);//Tells Spring Security to use JDBC authentication with our datasource.
//	}

	@Bean	//Using custom schema tables instead of Spring Security default schema.
	public UserDetailsManager userDetailsManager(DataSource dataSource){

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		//Retrieve user by username query.
		jdbcUserDetailsManager
				.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");//? is place holder for the 														//username entred by the client.

		//Retrieve roles/authorities by username query.
		jdbcUserDetailsManager
				.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

		return jdbcUserDetailsManager;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception //The security filters are defined using the HttpSecurity DSL.
	{
		httpSecurity.authorizeHttpRequests(customizer->			//authorizeHttpRequests throws Exception
				customizer.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")//ROLE_ is automatically prepended when using hasRole
						.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")//If u add it, it will throw an exception.
						.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
						.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
						.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAuthority("ROLE_ADMIN"));//U can add ROLE_ to hasAuthority though.

		httpSecurity.httpBasic(Customizer.withDefaults()); //Use HTTP basic authentication

		httpSecurity.csrf(AbstractHttpConfigurer::disable);

		return httpSecurity.build(); //Returns defaultSecurityFilterChain.
	}



	//	@Bean	//Create a bean named userDetailsManger based on the return type of the method.
//	public InMemoryUserDetailsManager userDetailsManager(){  // U can now inject userDetailsManger bean.
//
//		UserDetails mmd = User.builder()
//				.username("mmd")
//				.password("{noop}test123")
//				.roles("Employee", "Manager", "Admin")
//				.build();
//
//		UserDetails ah = User.builder()
//				.username("ah")
//				.password("{noop}test123")
//				.roles("Employee", "Manager")
//				.build();
//
//		UserDetails susan = User.builder()
//				.username("susan")
//				.password("{noop}test123")
//				.roles("Employee")
//				.build();
//		return new InMemoryUserDetailsManager(mmd, ah, susan);
//	}

}
