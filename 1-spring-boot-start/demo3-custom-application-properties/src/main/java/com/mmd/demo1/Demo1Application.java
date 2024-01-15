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

		Integer intW = Integer.valueOf("4",1);
		Boolean bool = Boolean.valueOf("true");
		Double doub = Double.valueOf("45.454");
		Float flo = Float.valueOf("4.454f");
		Short sho = Short.valueOf("32000");
		Character character = Character.valueOf('a');

		Integer intWw = Integer.parseInt("4",1);
		Boolean booll = Boolean.parseBoolean("true");
		Double doubb = Double.parseDouble("45.454");
		Float floo = Float.parseFloat("4.454f");
		Short shoo = Short.parseShort("32000");


	}



}
