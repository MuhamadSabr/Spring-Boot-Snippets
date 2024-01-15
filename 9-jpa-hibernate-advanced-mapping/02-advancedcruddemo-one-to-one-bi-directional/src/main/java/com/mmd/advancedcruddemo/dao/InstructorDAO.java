package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.Instructor;
import com.mmd.advancedcruddemo.entity.InstructorDetail;

public interface InstructorDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int instructorId);

    void deleteInstructorById(int InstructorId);


    InstructorDetail findInstructorDetailById(int instructorDetailId);

    void deleteInstructorDetailById(int instructorDetailId);

}
