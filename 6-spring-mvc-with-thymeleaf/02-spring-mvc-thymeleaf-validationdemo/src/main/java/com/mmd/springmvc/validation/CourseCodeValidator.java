package com.mmd.springmvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeValidator//Required to implement ConstraintValidator<UrCustomAnnotation, FieldOrMethod type to apply it to>
		implements ConstraintValidator<CourseCode, String> {

	private String coursePrefix;

	@Override
	public void initialize(CourseCode courseCode){//(Optional) Once the validator is initialized, the initialize() method is called.
		coursePrefix = courseCode.value();	//U get a handle of the actual annotation they use(its attributes' values)
	}											//Here we retrieve the value attribute's value to examine it against our rules.

	@Override//The first value is the actual form field data that the client enters. U can use the 2nd arg to place additional err msgs
	public boolean isValid(String htmlFormValue, ConstraintValidatorContext constraintValidatorContext){
		if(htmlFormValue.isBlank()) return true;  //If the value is null return true. isBlank() also replaces null checking.

		return htmlFormValue.startsWith(coursePrefix); //Else return true if it starts with the prefix or false if it does not.
	}
	//So by getting the actual courseCode.value() of the annotation while it's created, and comparing it by the html from field data
							//U conclude whether the client entered compliant data to the annotation value or not.
}
