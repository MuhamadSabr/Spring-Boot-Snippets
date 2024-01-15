package com.mmd.springmvc.model;

import com.mmd.springmvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

	private String firstName;

	//Add @NotNull constraint, define error message attribute.
	//Make @Size(min =1) and set error message attribute too, default min=0
	@NotNull(message = "Required")
	@Size(min = 1, message = "is required")//default min = 0
	@Pattern(regexp = "[a-z-A-Z]+", message = "Only letters are allowed here")
	private String lastName;

	//Imposing range on a numerical field
	@NotNull(message = "Required")
	@Max(value = 10, message = "Must be equal to or less than ten")
	@Min(value = 0, message = "Must be equal to or greater than zero")
	private Integer freePasses;


	//Add a postal code rule that it has to be exactly five characters of letters/digits
	@Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Has to be exactly 5 letters/digits")
	private String postalCode;

	//Add a course field and add ur custom validation annotation(CourseCode)
	@CourseCode(value = "LUV", message = "Must start with LUV")
	private String courseCode;

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getFreePasses() {
		return freePasses;
	}

	public void setFreePasses(Integer freePasses) {
		this.freePasses = freePasses;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
