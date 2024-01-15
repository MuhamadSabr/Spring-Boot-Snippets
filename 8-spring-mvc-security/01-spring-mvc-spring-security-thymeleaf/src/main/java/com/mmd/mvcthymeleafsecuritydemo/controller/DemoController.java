package com.mmd.mvcthymeleafsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	//Spring considers it when handling incoming HTTP requests.
public class DemoController {

	@GetMapping("/showLoginPage")
	public String showLoginPage(){

		return "login-page";
	}

	@GetMapping("/") //Provides routing information, Tells Spring that any HTTP request with Get method should be mapped to showHome method controller.
	public String showHome(){

		return "home"; //@Controller tells Spring to render the resulting string directly back to the caller
	}

	@GetMapping("/leaders")
	public String leadersPage(){
		return "leader-page";
	}

	@GetMapping("/systemAdmins")
	public String systemsPage(){
		return "system-page";
	}

	@GetMapping("/accessDenied")
	public String accessDeniedPage(){
		return "access-denied";
	}

}
