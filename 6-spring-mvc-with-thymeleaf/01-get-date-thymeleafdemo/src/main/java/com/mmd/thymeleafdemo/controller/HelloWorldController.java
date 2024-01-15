package com.mmd.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController{

	@GetMapping("/showForm")
	public String showForm(){
		String view = "helloworld-form";
		return view;
	}

	@GetMapping("/processForm")
	public String processForm(){
		String view = "helloworld";
		return view;
	}

	@GetMapping("/showFormVersionTwo")
	public String formTwo(){
		String view = "helloworld-form2";
		return view;
	}

	@GetMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model)//HttpServletRequest holds the form data that is sent to this address(/processFormVersionTWo)
	{
		//instead of using "${param.fieldName} in an html template, u get the form field data this way using HttpServerletRequest.
		String studentName = request.getParameter("studentName");

		studentName = studentName.toUpperCase();
		String shout = "YO " + studentName;

		//Add the parameter field data after it was processed into your model. Then use the model attribute in your html template using Thymeleaf.
		model.addAttribute("shout", shout);

		String view = "helloworld2";
		return view;
	}

	@GetMapping("/showFormRequestParamVersion")
	public String showFormRequestParamVersion(){
		return "helloworld-form3";
	}
	@PostMapping("/RequestParamVersion")
	public String bindParameter(@RequestParam("studentName") String theName, Model model){

		String shout = "YO " + theName.toUpperCase() + " Yo!";
		model.addAttribute("shout", shout);
		return "helloworld3";
	}
}
