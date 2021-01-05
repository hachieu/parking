package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Employee;
import com.entity.Role;
import com.entity.User;

@Controller
@RequestMapping("/account")
public class UserController {
	private RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/add")
	public String showForm(Model model) {
		model.addAttribute("user", new User());
		return "account";
	}
	
	@PostMapping
	public String save(User user, HttpSession session, Model model) {	
		
		if(user!=null) {
			boolean email = restTemplate.getForObject("http://localhost:8080/user/check-email/{email}", Boolean.class, user.getActive());
			if(!email) {
				Role role = restTemplate.getForObject("http://localhost:8080/role/detail/{id}", Role.class, "1");
				List<Role> roles = new ArrayList<>();
				roles.add(role);
				user.setRoles(roles);
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setEmail("unactive");
				session.setAttribute("user", user);
		
				return "redirect:/account/form-profile";
			}else {
				return "redirect:/account/add?exist";
			}
		}else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/form-profile")
	public String showFormProfile(Model model, HttpSession session, @RequestParam(value = "message", required = false) String message) {
		User user = (User) session.getAttribute("user");
		
		if(user!=null) {
			model.addAttribute("employee", new Employee());
			model.addAttribute("message", message);
			return "profile";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/save-profile")
	public String saveProfile(RedirectAttributes redirectAttributes,@RequestParam("fullName") String fullName,
			@RequestParam("birthDate") String birthDate,
			@RequestParam("gender") String gender,
			@RequestParam("identity") String identity,
			@RequestParam("phone") String phone,
			@RequestParam("address") String address
			, HttpSession session) {
		
		Random rd = new Random();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String id = "PK";

		for(int i=0;i<8;i++) {
			int value = rd.nextInt(10);
			id = id+value;
		}
		
		Date date = null;
		try {
			date = formatter.parse(birthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		User user = (User)session.getAttribute("user");
		Employee employee = new Employee(id,fullName,date,gender,identity,phone,address, user);
		
		if(employee!=null && user!=null) {
			boolean checkIdentity = restTemplate.getForObject("http://localhost:8080/employee/identity/{value}", Boolean.class, identity);
			if(!checkIdentity) {
				boolean checkPhone = restTemplate.getForObject("http://localhost:8080/employee/phone/{value}", Boolean.class, phone);
				if(!checkPhone) {
					restTemplate.postForObject("http://localhost:8080/user", user, User.class);
					
					User getUser = restTemplate.getForObject("http://localhost:8080/user/find-active/{email}", User.class, user.getActive());
					employee.setUser(getUser);
					restTemplate.postForObject("http://localhost:8080/employee", employee, Employee.class);
					session.removeAttribute("user");
					redirectAttributes.addAttribute("message", "success");
				}else {
					redirectAttributes.addAttribute("message", "phone");
				}
			}else {
				redirectAttributes.addAttribute("message", "identity");
			}
			
		}
		return "redirect:/account/form-profile";
	}
}
