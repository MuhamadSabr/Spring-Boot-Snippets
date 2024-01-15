package com.mmd.crudrestdemo.dao;

import com.mmd.crudrestdemo.entity.EmployeeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Mark this class as a class that performs data access operations.
public class EmployeeDAOImpl implements EmployeeDAO{

    //Define the entityManager
    private EntityManager entityManager; //Remember an entity manager is created routinely by Spring JPA.

    @Autowired  //Inject the systematically created entityManager into your application.
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        TypedQuery<EmployeeEntity> employees=  entityManager.createQuery("FROM EmployeeEntity", EmployeeEntity.class);
        return employees.getResultList();
    }

    @Override
    public EmployeeEntity findById(int id) {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, id);
        if(employee==null)
            throw new RuntimeException("Employee ID not found - " + id);
        return employee;
    }

    @Override
    public EmployeeEntity deleteById(int id) {
        EmployeeEntity employee = findById(id);
        if(employee==null){
            throw new RuntimeException("Employee ID not found - " + id);
        }else{
            entityManager.remove(employee);
            return employee;
        }
    }

    @Override
    public EmployeeEntity save(EmployeeEntity employee){
        return entityManager.merge(employee);  //Again persist-or-merge. If the entity exists, it persists(inserts), otherwise it will merge(update) it.
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        entityManager.persist(employee);
        return employee;
    }
}
