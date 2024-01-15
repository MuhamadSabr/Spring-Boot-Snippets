package com.mmd.crudrestdemo.rest;

import com.mmd.crudrestdemo.entity.EmployeeEntity;
import com.mmd.crudrestdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")     //The base mapping of api
public class EmployeeRestController {

    //Make use of the EmployeeService instead of directly accessing the DAO.
    private final EmployeeService employeeService;

    @Autowired  //Inject the employee service instead of the DAO.
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //End-point to return a list of employees
    @GetMapping("/employees")
    public List<EmployeeEntity> getEmployees(){
        return employeeService.findAll();
    }

    //End-point to return a single employee
    @GetMapping("/employees/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable int employeeId){
        return employeeService.findById(employeeId);
    }

    //End-point to delete a single employee
    @DeleteMapping("/employees/{employeeId}")
    public EmployeeEntity deleteEmployee(@PathVariable int employeeId){
        return employeeService.deleteById(employeeId);
    }

    //End-point for inserting a new employee.
    @PostMapping("/employees")
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee){    //otherwise U will face 'detached entity passed to persist' error.
        return employeeService.addEmployee(employee);   //If you use persist method then u have to make sure the client does not specify an employeeId in the request.
//        return employeeService.save(employee);
    }

    //End-point for updating an existing entity
    @PutMapping("/employees")
    public EmployeeEntity update(@RequestBody EmployeeEntity employee){
        if(employee.getEmployeeId()==0) //Remember U don't set the employeeId in EmployeeEntity, so if nothing passed from the DB table 0 is the default value for int vars
            throw new RuntimeException("Employee ID not found - " + employee.getEmployeeId());
        return employeeService.save(employee);
    }

}
