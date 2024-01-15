package com.mmd.crudrestdemo.dao;

import com.mmd.crudrestdemo.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO {

    List<EmployeeEntity> findAll();

    EmployeeEntity findById(int id);

    EmployeeEntity deleteById(int id);

    //Persist-or-merge. If the entity does not exist merge will call persist and add the entity to the DB table. This insert, comes at a cost of having to check
    //the entire DB table and then adding it if it does not exist.
    EmployeeEntity save(EmployeeEntity employee);

    EmployeeEntity addEmployee(EmployeeEntity employee);
}
