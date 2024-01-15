package com.mmd.springmvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD}) //Where the custom annotation can be applied.
@Retention(RetentionPolicy.RUNTIME)	//The annotation will be available at run-time, which is necessary for validation.
@Constraint(validatedBy = CourseCodeValidator.class)//The validator class that should handle the validation logic/rule for this annotation.
public @interface CourseCode {																	 //validatedBy attribute creates the link

	//Adding message attribute and specifying its default value if omitted.
	String message() default "Must start with MMD";

	//Adding value attribute and specifying its default value if omitted.
	String value() default "MMD";

	//Define default groups, and default payload
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default  {};
}
