package com.eslam.springboot.thymeleafdemo.controller;

import com.eslam.springboot.thymeleafdemo.entity.Employee;
import com.eslam.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService = employeeService;
	}
	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// add to the spring model
		List<Employee> theEmployees = employeeService.findAll();
		theModel.addAttribute("employees", theEmployees);

		return "list-employees";
	}
	@GetMapping("/showAddForm")
	public String showAddForm(Model model)
	{
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "employee-form";
	}


	@GetMapping("/showUpdateForm")
	public String showUpdateForm(@RequestParam("employeeId") int id, Model model)
	{
		Employee employee=employeeService.findById(id);
		model.addAttribute("employee",employee);
		return "employee-form";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id)
	{
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.save(employee);

		return "redirect:/employees/list";
	}
}









