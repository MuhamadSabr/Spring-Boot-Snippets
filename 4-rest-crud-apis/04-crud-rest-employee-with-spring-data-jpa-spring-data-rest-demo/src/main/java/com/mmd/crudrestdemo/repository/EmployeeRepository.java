package com.mmd.crudrestdemo.repository;

import com.mmd.crudrestdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //Extend the JpaRepository<entity, primaryKey>, pass these two arguments, and you have a crud implementation ready for DI

    List<Optional<Employee>> findByLastName(String lastName);
}
