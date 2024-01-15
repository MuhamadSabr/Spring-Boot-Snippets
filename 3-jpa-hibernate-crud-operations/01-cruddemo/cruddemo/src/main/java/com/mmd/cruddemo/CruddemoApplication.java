package com.mmd.cruddemo;

import com.mmd.cruddemo.dao.StudentDAO;
import com.mmd.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	//Here u inject the StudentDAO
	@Bean  //This is for creating a command-line application										
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){//The CommandLineRunner is from the Spring Boot framework. This code 											//will be executed after the beans have been loaded
		return runner ->{
			createStudent(studentDAO);
//			studentDAO.findAll().forEach(System.out::println);
//			findByLastName("Jawhar", studentDAO);
//			studentDAO.findByFullName("Mohammed", "Ohman").forEach(System.out::println);
//			updateStudent(studentDAO);
//			System.out.println( studentDAO.updateAll() + " numrowsupdated");
//			System.out.println(studentDAO.deleteAll() + " numrowsdeleted");
//			studentDAO.deleteStudent(8);
		};
	}

	private void updateStudent(StudentDAO studentDAO){
		Student student = readStudent(studentDAO);
		if(student!=null) {
			student.setLastName("Shafiq");
			System.out.println(studentDAO.update(student));
		}
		else
			System.out.println("No such student");
	}
	private void findByLastName(String lastName, StudentDAO studentDAO){
		studentDAO.findByLastName(lastName).forEach(System.out::println);
	}
	private Student readStudent(StudentDAO studentDAO){
		return studentDAO.findById(7);
	}
	private void createStudent(StudentDAO studentDAO) {
		//Create the student obj
		Student student = new Student("Ahmed", "Karim", "karimAh123@Gmail.com");
		studentDAO.save(student);

		student = new Student("Mohammed", "Othman", "mahamad14@Gmail.com");
		studentDAO.save(student);

		student = new Student("Jafar", "Kadr", "K123Jafar@Gmail.com");
		studentDAO.save(student);
	}

}
