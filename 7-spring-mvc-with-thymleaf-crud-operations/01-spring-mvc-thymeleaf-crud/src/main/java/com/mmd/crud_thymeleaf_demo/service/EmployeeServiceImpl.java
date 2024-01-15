package com.mmd.crud_thymeleaf_demo.service;

import com.mmd.crud_thymeleaf_demo.entity.EmployeeEntity;
import com.mmd.crud_thymeleaf_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service    //This also makes the class a Spring bean, meaning will be constructed at the app start-up and managed by Spring Boot
                                                                                //Making it a candidate for DI.
public class EmployeeServiceImpl implements EmployeeService{

    //Define EmployeeRepository
    private final EmployeeRepository employeeRepository;

    @Autowired  //Inject the DAO.
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeEntity> findById(int id){
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeEntity save(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

    @Override
//    @Transactional //JpaRepository takes care of the transaction so no need to make it transactional at this level
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

    @Override
    public List<Optional<EmployeeEntity>> findByLastName(String lastName){
        return employeeRepository.findByLastName(lastName);
    }

    @Override
    public List<EmployeeEntity> findAllByOrderByLastNameAsc() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }
}
