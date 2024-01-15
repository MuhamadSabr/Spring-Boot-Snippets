package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.*;

import java.util.List;

public interface InstructorDAO {

    void save(Instructor instructor);

    void save(Course course);

    void save(Student student);

    Instructor findInstructorById(int instructorId);

    List<Instructor> findAllInstructors();

    void deleteInstructorById(int InstructorId);


    InstructorDetail findInstructorDetailById(int instructorDetailId);

    void deleteInstructorDetailById(int instructorDetailId);

    List<Course> findCourseByInstructorId(int instructorId);

    Course findCourseById(int courseId);

    Course findCourseByTitle(String title);

    Instructor findInstructorByIdWithAssociatedCourses(int instructorId);

    void update(Instructor instructor);

    void update(Course course);

    void update(Student student);

    void deleteCourse(int courseId);

    Course findCourseByIdWithReviews(int courseId);

    Student findStudentById(int studentId);

    void deleteStudentById(int studentId);

    Course findCourseByIdWithStudents(int courseId);

    Student findStudentByIdWithCourses(int studentId);

}
