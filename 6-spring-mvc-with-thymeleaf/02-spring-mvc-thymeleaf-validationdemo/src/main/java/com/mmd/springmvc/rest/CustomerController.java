package com.mmd.springmvc.rest;

import com.mmd.springmvc.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

	private final String customerFormView = "customer-form";
	private final String customerConfirmationView = "customer-confirmation";

	@InitBinder
	public void initBinder(WebDataBinder binder){//U use WebDataBinder to customize the data binding process

		//passing true, empty strings will be set to null, passing false empty strings will not be set to null.
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
		binder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	//Before u show the form view, u have to add the object to the model, to be stored and used and displayed by its assocaited view.
	@GetMapping("/")
	public String showForm(Model model){//Remember each model is associated with the view of the controller method it is used for.

		model.addAttribute("customer", new Customer());

		//Associates with the model through this controller.
		return customerFormView;
	}

	//Retrieve, perform validation on and process the form data bound to the Customer model object.
	@PostMapping("/processForm")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer customer, //@Valid tells Spring MVC to validate model attribute "customer".
			BindingResult bindingResult){//The Result validation of the form bound to customer is held in this

//		System.out.println("Last Name |" + customer.getLastName() +"|");
		System.out.println("BindingResult: " + bindingResult.toString());

		//Write logic base on the validation result and customer data.
		if(bindingResult.hasErrors())
			return customerFormView;
		else
			return customerConfirmationView;
	}
}
