package com.mmd.advancedcruddemo;

import com.mmd.advancedcruddemo.dao.InstructorDAO;
import com.mmd.advancedcruddemo.entity.Instructor;
import com.mmd.advancedcruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedcruddemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO){//When a bean is created, there is no need for @Autowired for DI

		return runner ->{

//			createInstructor(instructorDAO);
//			instructorDAO.deleteInstructorById(3);
//			printInstructor(instructorDAO, 3);

//			printInstructorDetail(instructorDAO, 1);
			instructorDAO.deleteInstructorDetailById(4);

		};
	}

	public void printInstructorDetail(InstructorDAO instructorDAO, int instructorDetailId){

		System.out.println("Instructor Detail: " + instructorDAO.findInstructorDetailById(instructorDetailId));
		System.out.println("Instructor : " + instructorDAO.findInstructorDetailById(instructorDetailId).getInstructor());
	}

	public void printInstructor(InstructorDAO instructorDAO, int instructorId){
		System.out.println(instructorDAO.findInstructorById(instructorId));
	}

	public void createInstructor(InstructorDAO instructorDAO){

		//Create the instructor object.
		Instructor mohammedInstructor = new Instructor("Mohammed", "Othman", "mahamadsaber18@Gmail.com");


		//Set the Instructor detail of the instructor
		InstructorDetail mohammedInstructorDetail = new InstructorDetail("mmdYoutube.com", "Reading");

		//Associate the two objects together.
		mohammedInstructor.setInstructorDetail(mohammedInstructorDetail);


		//Insert the Instructor into the DB. Since the OneToOne relationship has CascadeType.ALL. The instructorDetail will also be inserted
		//into the corresponding table.
		instructorDAO.save(mohammedInstructor);

	}

}
