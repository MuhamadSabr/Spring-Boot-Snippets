package com.mmd.advancedcruddemo;

import com.mmd.advancedcruddemo.dao.InstructorDAO;
import com.mmd.advancedcruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AdvancedcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedcruddemoApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO){//When a bean is created, there is no need for @Autowired for DI

		return runner ->{

//			createCourseWithStudents(instructorDAO);
//			instructorDAO.save(new Course("J++"));
//			printCourseByIdWithStudents(instructorDAO, 14);
//			System.out.println(instructorDAO.findCourseById(15));
//			createStudent(instructorDAO);
//			instructorDAO.findCourseById(15).addStudent(instructorDAO.findStudentById(7));
//			instructorDAO.deleteStudentById(8);
			addCourseToStudent(instructorDAO, 9, 17);
			printStudentByIdWithCourses(instructorDAO, 9);
//			instructorDAO.deleteCourse(13);
		};
	}

	//Many to many

	public void addCourseToStudent(InstructorDAO instructorDAO, int studentId, int courseId){
		Course course = instructorDAO.findCourseById(courseId);
		if(course==null) course = new Course("Zyada");
		Student student = instructorDAO.findStudentByIdWithCourses(studentId);

		System.out.println("Adding the course...");
		student.addCourse(course);
		instructorDAO.update(student);
	}
	public void printStudentByIdWithCourses(InstructorDAO instructorDAO, int studentId){
		Student student = instructorDAO.findStudentByIdWithCourses(studentId);
		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	public void createStudent(InstructorDAO instructorDAO){
		Student student = new Student("Xarman", "Shorsh", "shorsh.gulan.com");
		instructorDAO.save(student);
	}

	public void printCourseByIdWithStudents(InstructorDAO instructorDAO, int courseId){
		Course course = instructorDAO.findCourseByIdWithStudents(courseId);
		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}
	public void createCourseWithStudents(InstructorDAO instructorDAO){

		//Create course
		Course courseCpp = new Course("C++");


		courseCpp.addStudent( new Student("Mohammed", "Saber", "mahamadsaber18@gmail.com") ) ;
		courseCpp.addStudent( new Student("Karim", "Jawhar", "jawhar.com") ) ;
		courseCpp.addStudent( new Student("Shafiq", "Toffiq", "sToufic.com") ) ;

		//Save the course and the associated students
		instructorDAO.save(courseCpp);
	}

	//Update Instructor
	public void updateInstructor(InstructorDAO instructorDAO, Instructor instructor){
		instructorDAO.update(instructor);
	}

	//Instructor and Course part
	public void printInstructorJoinedWithCourses(InstructorDAO instructorDAO, int instructorId){

		//Fetch the main entity along with its associated courses, in an eager fashion.
		//Helps you avoid the N+1 select problem, where N number of queries are executed to retrieve N related entities.
		Instructor instructor = instructorDAO.findInstructorByIdWithAssociatedCourses(instructorId);

		System.out.println(instructor);
		System.out.println(instructor.getCourses());
	}

	public void printInstructorWithCourse(InstructorDAO instructorDAO, int instructorId){

		//OneTOMany relationship exists between instructor and course with its default fetch strategy being LAZY, the course entity won't be loaded.
		//But instructor detail entity will be loaded since its relationship with the owning side(instructor) is OneToOne and default fetch strategy of it is EAGER.
		Instructor instructor = instructorDAO.findInstructorById(instructorId);
		System.out.println(instructor);

		//You can associate the returned courses by the query that loads courses by instructor id, or you can use it directly.
		instructor.setCourses( instructorDAO.findCourseByInstructorId(instructorId) );
		System.out.println(instructor.getCourses());
	}
	public void createInstructorWithCourse(InstructorDAO instructorDAO, Instructor instructor){

		//Because of CascadeType.PERSIST the related records will also be added to their corresponding tables.
		instructorDAO.save(instructor);
		System.out.println("Saving Instructor: " + instructor);
		System.out.println("Their details: " + instructor.getInstructorDetail());
		System.out.println("Their courses: " + instructor.getCourses());
	}


	//Instructor and Instructor Detail part
	public void printInstructorDetail(InstructorDAO instructorDAO, int instructorDetailId){

		System.out.println("Instructor Detail: " + instructorDAO.findInstructorDetailById(instructorDetailId));
		System.out.println("Instructor : " + instructorDAO.findInstructorDetailById(instructorDetailId).getInstructor());
	}

	public void printInstructor(InstructorDAO instructorDAO, int instructorId){
		Instructor instructor = instructorDAO.findInstructorById(instructorId);
		System.out.println(instructor);
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
