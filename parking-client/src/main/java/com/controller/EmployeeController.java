package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.entity.Employee;
import com.entity.User;

@Controller
@RequestMapping("/parking/employee")
public class EmployeeController {
	
//	@Autowired
//	private IAuthenticationFacade authenticationFacade;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee";
	}
	
	@GetMapping("/current")
	public String findAll(Model model) {
		List<Employee> listEmployee = Arrays.asList(restTemplate.getForObject("http://localhost:8080/employee/current", Employee[].class));
		model.addAttribute("employee", listEmployee);
		return "listemployee";
	}
	
	@GetMapping("/detail/{id}")
	public String detailEmployee(Model model, @PathVariable String id) {
		Employee getEmployee = restTemplate.getForObject("http://localhost:8080/employee/find-id/{id}", Employee.class, id);
		model.addAttribute("employee", getEmployee);
		return "updateemployee";
	}
	
	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable String id, 
			@RequestParam("fullName") String fullName,
			@RequestParam("birthDate") String birthDate,
			@RequestParam("gender") String gender,
			@RequestParam("identity") String identity,
			@RequestParam("phone") String phone,
			@RequestParam("address") String address) {
//		Authentication authentication = authenticationFacade.getAuthentication();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(birthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		User user = restTemplate.getForObject("http://localhost:8080/user/detail/{id}", User.class, id);
		Employee getEmployee = restTemplate.getForObject("http://localhost:8080/employee/find-id/{id}", Employee.class, id);
		
		Employee employee = new Employee(getEmployee.getId(),fullName,date,gender,identity,phone,address, user);
		restTemplate.put("http://localhost:8080/employee/update/{id}", employee, Employee.class, id);
		return "redirect:/parking/employee/current";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable String id) {
		restTemplate.delete("http://localhost:8080/employee/delete/{id}", id);
		return "redirect:/parking/employee/current";
	}

}
