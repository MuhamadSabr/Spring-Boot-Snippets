package com.mmd.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller  //Indicate controller methods would return an html page, a view or both(html rendering with embedded data).
public class DemoController {

	//Creat a mapping for sayDate.
	@GetMapping("/sayDate")
	public String sayDate(Model model)//Spring will create an instance of the Model and inject it here.
	{
		model.addAttribute("date", LocalDateTime.now());
		return "datedata";
	}
}
