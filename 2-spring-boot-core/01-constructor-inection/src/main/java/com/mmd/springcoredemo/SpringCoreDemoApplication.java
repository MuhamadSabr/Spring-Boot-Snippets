package com.mmd.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;//Enables Auto configuration, Component scanning, Additional configuration.
																	//@EnableAutoConfiguration,  @ComponentScan, 	 @Configuration


@SpringBootApplication
public class SpringCoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}

}
