package com.mmd.thymeleafdemo.controller;

import com.mmd.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

	//Inject countries list from the application.properties.
	@Value("${countries}")
	private List<String> countries;

	//Inject favorite languages' list from the application.properties.
	@Value("${favoriteLanguages}")
	private List<String> favoriteLanguages;

	//Inject the list of OSs from the application.properties
	@Value("${operatingSystems}")
	List<String> operatingSystems;

	@GetMapping("/showStudentForm")
	public String showForm(Model model){

		//Before u load the form, U need to add the object as an attribute to the model.
		model.addAttribute("student", new Student());

		//Add the countries' list to the model.
		model.addAttribute("countries", countries);

		//Add the favoriteLanguage list to the model.
		model.addAttribute("favoriteLanguages", favoriteLanguages);

		//Add the OSs list to the model
		model.addAttribute("operatingSystems", operatingSystems);

		String view = "student-form";
		return view;
	}

	@PostMapping("/processStudentForm")
	public String processForm(@ModelAttribute("student") Student student){//This way u inject the student object in from Model

		System.out.printf("Student: %d: %s %s %s%n",student.getStudentId(),
				student.getFirstName(), student.getLastName(),student.getCountry());
		System.out.println("Favorite programming language: " + student.getFavoriteLanguage());
		System.out.println("Favorite OSs: " + student.getFavoriteOSs());

		String view = "student-confirmation";
		return view;
	}

}
