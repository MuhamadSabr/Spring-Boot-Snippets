package com.mmd.crudrestdemo.service;

import com.mmd.crudrestdemo.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    List<EmployeeEntity> findAll();

    Optional<EmployeeEntity> findById(int id);

    void deleteById(int id);

    EmployeeEntity save(EmployeeEntity employee);

    List<Optional<EmployeeEntity>> findByLastName(String lastName);

    EmployeeEntity addEmployee(EmployeeEntity employee);
}
