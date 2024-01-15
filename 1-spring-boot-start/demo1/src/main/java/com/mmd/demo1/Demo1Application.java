package com.mmd.demo1;

import com.mmd.demo1.rest.RestCotrollerClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		//This piece of code bellow Bootstraps the Spring Boot application, This class is passed to it to run
		SpringApplication.run(Demo1Application.class, args);

		RestCotrollerClass hi = new RestCotrollerClass();
		hi.greetings();
	}

}
