package com.controller;

import java.util.Arrays;
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
import com.entity.Role;
import com.entity.User;

@Controller
@RequestMapping("/parking/account")
public class CustomUser {
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/current")
	public String findAll(Model model) {
		List<Employee> listEmployee = Arrays.asList(restTemplate.getForObject("http://localhost:8080/employee/current", Employee[].class));
		model.addAttribute("employee", listEmployee);
		return "listaccount";
	}
	
	@PostMapping("/active/{id}")
	public String active(@PathVariable String id, @RequestParam String email) {
		User user = restTemplate.getForObject("http://localhost:8080/user/find-active/{email}", User.class, email);
		List<Role> roles = user.getRoles();
		User active = new User(user.getId(), email, user.getPassword(), user.getActive(), roles);
		active.setId(Integer.parseInt(id));
		restTemplate.put("http://localhost:8080/user/active/{id}", active, User.class, active.getId());
		return "redirect:/parking/account/current?active";
	}
}
