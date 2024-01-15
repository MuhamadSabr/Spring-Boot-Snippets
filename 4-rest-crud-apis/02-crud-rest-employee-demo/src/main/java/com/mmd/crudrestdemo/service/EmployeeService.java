package com.mmd.crudrestdemo.service;

import com.mmd.crudrestdemo.dao.EmployeeDAO;
import com.mmd.crudrestdemo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeEntity> findAll();

    EmployeeEntity findById(int id);

    EmployeeEntity deleteById(int id);

    EmployeeEntity save(EmployeeEntity employee);

    EmployeeEntity addEmployee(EmployeeEntity employee);
}
