package com.mmd.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;//Enables Auto configuration, Component scanning, Additional configuration.
																	//@EnableAutoConfiguration,  @ComponentScan, 	 @Configuration


@SpringBootApplication(//When u define ur own packages to be scanned, U have to include every  package, including the one that
		scanBasePackages = {"com.mmd.springcoredemo", "com.mmd.util"}//by default was component scanned. Otherwise won't work
)
public class SpringCoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}
}
