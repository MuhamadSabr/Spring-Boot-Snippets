package com.mmd.crudrestdemo.repository;

import com.mmd.crudrestdemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    //Extend the JpaRepository<entity, primaryKey>, pass these two arguments, and you have a crud implementation ready for DI

    List<Optional<EmployeeEntity>> findByLastName(String lastName);
}
