package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Register;
import com.entity.Ticket;

@Controller
@RequestMapping("/parking/register")
public class RegisterController {
private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showFormRegister(Model model, @RequestParam(value = "message", required = false) String message) {
		model.addAttribute("register", new Register());
		List<Ticket> listTicket = Arrays.asList(restTemplate.getForObject("http://localhost:8080/ticket/current", Ticket[].class));
		model.addAttribute("ticket", listTicket);
		model.addAttribute("message", message);
		return "register";
	}
	
	@GetMapping("/current")
	public String listRegister(Model model) {
		List<Register> listRegister = Arrays.asList(restTemplate.getForObject("http://localhost:8080/register/current", Register[].class));
		model.addAttribute("listregister", listRegister);
		return "listregister";
	}
	
	@PostMapping
	public String postRegister(RedirectAttributes redirectAttributes, 
			@RequestParam String licencePlate, 
			@RequestParam String nameCustomer,
			@RequestParam String birthDate,
			@RequestParam String ticket) {
		boolean result = restTemplate.getForObject("http://localhost:8080/register/isempty/{id}", Boolean.class, licencePlate);
		if(!result) {
			LocalDate now = LocalDate.now();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String nowString = String.valueOf(now);
			Date birth = null;
			Date regDate = null;
			try {
				birth = formatter.parse(birthDate);
				regDate = formatter.parse(nowString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Ticket detail = restTemplate.getForObject("http://localhost:8080/ticket/detail/{id}", Ticket.class, ticket);
			Register register = new Register(licencePlate,nameCustomer,birth,regDate,detail);
			restTemplate.postForObject("http://localhost:8080/register", register, Register.class);
			redirectAttributes.addAttribute("message", "success");
		}else {
			redirectAttributes.addAttribute("message", "exist");
		}
		return "redirect:/parking/register/add";
	}
	
	@GetMapping("/detail/{id}")
	public String detailRegister(Model model, @PathVariable String id, @RequestParam(value = "message", required = false) String message) {
		Register detail = restTemplate.getForObject("http://localhost:8080/register/detail/{id}", Register.class, id);
		List<Ticket> listTicket = Arrays.asList(restTemplate.getForObject("http://localhost:8080/ticket/current", Ticket[].class));
		model.addAttribute("ticket", listTicket);
		model.addAttribute("register", detail);
		model.addAttribute("message", message);
		return "updateregister";
	}
	
	@PostMapping("/update/{id}")
	public String updateRegister(@PathVariable String id, RedirectAttributes redirectAttributes,
			@RequestParam String licencePlate, 
			@RequestParam String nameCustomer,
			@RequestParam String birthDate,
			@RequestParam String ticket) {
		boolean result = restTemplate.getForObject("http://localhost:8080/register/isempty/{id}", Boolean.class, licencePlate);
		if(result) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date birth = null;

			try {
				birth = formatter.parse(birthDate);
	
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Register getIssue = restTemplate.getForObject("http://localhost:8080/register/detail/{id}", Register.class, id);
			Ticket detail = restTemplate.getForObject("http://localhost:8080/ticket/detail/{id}", Ticket.class, ticket);
			Register register = new Register(licencePlate,nameCustomer,birth,getIssue.getDateIssue(),detail);
			restTemplate.put("http://localhost:8080/register/update/{id}", register, Register.class, id);
		}else {
			redirectAttributes.addAttribute("message", "exist");
			return "redirect:/parking/register/detail/{id}";
		}
		
		return "redirect:/parking/register/current";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id) {
		restTemplate.delete("http://localhost:8080/register/delete/{id}", id);
		return "redirect:/parking/register/current";
	}
}
