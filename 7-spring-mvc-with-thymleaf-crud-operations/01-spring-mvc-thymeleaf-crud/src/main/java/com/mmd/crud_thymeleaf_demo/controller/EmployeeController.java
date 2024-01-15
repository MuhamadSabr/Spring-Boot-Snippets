package com.mmd.crud_thymeleaf_demo.controller;

import com.mmd.crud_thymeleaf_demo.entity.EmployeeEntity;
import com.mmd.crud_thymeleaf_demo.service.EmployeeService;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

	//U need to make use of the EmployeeServiceImpl to perform CRUD operations on the EmployeeReposiroty
	private EmployeeService employeeService;

	//Define variables for the used view names
	private final String listEmployeeView = "employees/employee-list";
	private final String addEmployeeFormView = "employees/add-employee-form";
	private final String redirectToEmployeeListPage = "redirect:/employees/list";
	@Autowired//Inject the employeeService using constructor DI. Three is only one constructor so the @Autowired can be omitted
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

	//Add mapping for listing employees list
	@GetMapping(path = "/list")
	public String listEmployees(Model model){

		//Retrieve the list of employees, then sort it by firstName.
		List<EmployeeEntity> employees = employeeService.findAllByOrderByLastNameAsc();
//		List<EmployeeEntity> employees = employeeService.findAll();
//		employees.sort(Comparator.comparing(EmployeeEntity::getFirstName));

		//Add the list of employees to the model under name employeesList to be used n displayed by listEmployeeView
		model.addAttribute("employeesList", employees);

		return listEmployeeView;
	}

	//Add mapping for showing employee form
	@GetMapping("/showAddEmployeeForm")
	public String showAddEmployeeForm(Model model){

		//Add a new EmployeeEntity as a model attribute to bind the form data.
		model.addAttribute("employee", new EmployeeEntity());

		return addEmployeeFormView;
	}

	//Add mapping for updating an employee
	@GetMapping("/showUpdateEmployeeForm")
	public String showUpdateEmployeeForm(@RequestParam("employeeId") int employeeId, Model model){

		//Retrieve the employee form the service.
		Optional<EmployeeEntity> employee = employeeService.findById(employeeId);//Because the client will choose an id using the UI, there is no possibility of null

		//Add the specified employee to the model.
		model.addAttribute("employee", employee);

		//Re-use the addForm for update.
		return addEmployeeFormView;
	}

	//Add mapping to delete an employee
	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId){

		//Delete the employee by the employeeId through the service.
		employeeService.deleteById(employeeId);

		//Refresh the Employee List page
		return redirectToEmployeeListPage;
	}

	//Add processing add employee form mapping
	@PostMapping("/processAddEmployeeForm")		//Remember through @ModelAttribute("modelAttribute") U retrieve the form data.
	public String processAddEmployeeForm(@ModelAttribute("employee") EmployeeEntity employee){

		//If the sent employee's id is 0 then it will add it, which is the case when a new employee is added.
		//On the other hand, when through the Update, the data is loaded form DB, the id is not 0 and it's one that already exists, so it will be persist and not add.
		employeeService.save(employee);

		//Redirect the user to /employees/list
	 	return redirectToEmployeeListPage;
	}

}


<option th:each="dept : ${departments}" 
                th:value="${dept.id}" 
                th:text="${dept.name}" 
                th:selected="${dept.id == employee.departmentId}"></option>