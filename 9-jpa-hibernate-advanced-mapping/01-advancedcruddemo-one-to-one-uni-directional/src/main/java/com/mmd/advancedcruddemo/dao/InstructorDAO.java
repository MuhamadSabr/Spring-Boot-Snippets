package com.mmd.advancedcruddemo.dao;

import com.mmd.advancedcruddemo.entity.Instructor;

public interface InstructorDAO {

    void save(Instructor instructor);

    Instructor findById(int instructorId);

    void delete(int InstructorId);
}
