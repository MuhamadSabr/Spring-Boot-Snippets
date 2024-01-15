package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.Course;
import com.mmd.advancedcruddemo.entity.Instructor;
import com.mmd.advancedcruddemo.entity.InstructorDetail;
import com.mmd.advancedcruddemo.entity.Review;

import java.util.List;

public interface InstructorDAO {

    void save(Instructor instructor);

    void save(Course course);

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

    void deleteCourse(int courseId);

    Course findCourseByIdWithReviews(int courseId);

}
