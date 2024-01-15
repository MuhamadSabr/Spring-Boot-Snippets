package com.mmd.cruddemo.dao;

import com.mmd.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student student);

    Student findById(int studentId);

    List<Student> findByLastName(String lastName);

    List<Student> findAll();

    List<Student> findByFullName(String firstName, String lastName);

    Student update(Student student);

    int updateAll();

    void truncate();

    int deleteAll();

    void deleteStudent (int studentId);
}
