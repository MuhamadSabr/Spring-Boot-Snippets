package com.mmd.crudrestdemo.service;

import com.mmd.crudrestdemo.dao.EmployeeDAO;
import com.mmd.crudrestdemo.dao.EmployeeDAOImpl;
import com.mmd.crudrestdemo.entity.EmployeeEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    //This also makes the class a Spring bean, meaning will be constructed at the app start-up and managed by Spring Boot
                                                                                //Making it a candidate for DI.
public class EmployeeServiceImpl implements EmployeeService{

    //Define EmployeeDAO
    private EmployeeDAO employeeDAO;

    @Autowired  //Inject the DAO.
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<EmployeeEntity> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public EmployeeEntity findById(int id){
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public EmployeeEntity deleteById(int id) {
        return employeeDAO.deleteById(id);
    }

    @Override
    @Transactional
    public EmployeeEntity save(EmployeeEntity employee){
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        return employeeDAO.addEmployee(employee);
    }

}
