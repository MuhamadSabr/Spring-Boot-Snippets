package com.mmd.crudrestdemo.rest;

import com.mmd.crudrestdemo.entity.EmployeeEntity;
import com.mmd.crudrestdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //End-point to return a single employee by ID
    @GetMapping("/employees/{employeeIdOrLastName}")
    public List<Optional<EmployeeEntity>> getEmployeeById(@PathVariable String employeeIdOrLastName){
        List<Optional<EmployeeEntity>> empO = new ArrayList<>();
        if(employeeIdOrLastName.matches("\\d+")) {
            empO.add( employeeService.findById(Integer.parseInt(employeeIdOrLastName)) );
            return empO;
        }

        return  employeeService.findByLastName(employeeIdOrLastName) ;
    }


//    //End-point to return a single employee by lastName
//    @GetMapping("/employees/{lastName}")
//    public EmployeeEntity getEmployeeById(@PathVariable String lastName){
//        return employeeService.findByLastName(lastName);
//    }

    //End-point to delete a single employee
    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteById(employeeId);
    }

    //End-point for inserting a new employee.
    @PostMapping("/employees")
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee){    //otherwise U will face 'detached entity passed to persist' error.
        return employeeService.save(employee);
    }

    //End-point for updating an existing entity
    @PutMapping("/employees")
    public EmployeeEntity update(@RequestBody EmployeeEntity employee){
        if(employeeService.findById(employee.getEmployeeId()).isEmpty())
            throw new RuntimeException("Employee ID not found - " + employee.getEmployeeId());
        return employeeService.save(employee);
    }

}
