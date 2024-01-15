package com.mmd.advancedcruddemo;

import com.mmd.advancedcruddemo.dao.InstructorDAO;
import com.mmd.advancedcruddemo.entity.Course;
import com.mmd.advancedcruddemo.entity.Instructor;
import com.mmd.advancedcruddemo.entity.InstructorDetail;
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

//			createInstructor(instructorDAO);

//			printInstructorDetail(instructorDAO, 10);
//			instructorDAO.deleteInstructorDetailById(10);


			//Creating an instructor, setting its instructor detail and courses then saving it in the DB.
//			Instructor instructor = new Instructor("Mohammed", "Othman", "mahamadsaber18@gmail.com");
//			instructor.setInstructorDetail(new InstructorDetail("youtube.reading.com", "Teaching"));
//			List<Course> courses = new ArrayList<>(List.of(
//					new Course("C++"),
//					new Course("Java")
//			));
//			instructor.setCourses(courses);
//			createInstructorWithCourse(instructorDAO, instructor);


			//Updating an instructor part:
//			printInstructorJoinedWithCourses(instructorDAO, 12);
//			Instructor instructor = instructorDAO.findInstructorById(12); //We just want to update the Instructor data, does not make sense to load courses
//			instructor.setLastName("Saber");
//			updateInstructor(instructorDAO, instructor);
//			printInstructorJoinedWithCourses(instructorDAO, 12);

			//Updating a course part
//			Course course = instructorDAO.findCourseByTitle("C#");
//			Course course = instructorDAO.findCourseById(9);
//			course.setTitle("C++");
//			instructorDAO.update(course);

//			instructorDAO.findAllInstructors().forEach(System.out::println);

			//Delete instructor part
//			instructorDAO.deleteInstructorById(12);

			//Delete Course part
			instructorDAO.deleteCourse(9);
		};
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
