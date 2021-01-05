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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Ticket;

@Controller
@RequestMapping("/parking/ticket")
public class TicketController {
private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showFormTicket(Model model, @RequestParam(value = "message", required = false) String message) {
		model.addAttribute("ticket", new Ticket());
		model.addAttribute("message", message);
		return "ticket";
	}
	
	@GetMapping("/current")
	public String listTicket(Model model) {
		List<Ticket> listTicket = Arrays.asList(restTemplate.getForObject("http://localhost:8080/ticket/current", Ticket[].class));
		model.addAttribute("ticket", listTicket);
		return "listticket";
	}
	
	@PostMapping
	public String postTicket(Ticket ticket, RedirectAttributes redirectAttributes) {
		restTemplate.postForObject("http://localhost:8080/ticket", ticket, Ticket.class);
		redirectAttributes.addAttribute("message", "success");
		return "redirect:/parking/ticket/add";
	}
	
	@GetMapping("/detail/{id}")
	public String detailTicket(Model model, @PathVariable int id) {
		Ticket detail = restTemplate.getForObject("http://localhost:8080/ticket/detail/{id}", Ticket.class, id);
		model.addAttribute("ticket", detail);
		return "updateticket";
	}
	
	@PostMapping("/update/{id}")
	public String updateTicket(Ticket ticket, @PathVariable String id) {
		ticket.setId(Integer.parseInt(id));
		restTemplate.put("http://localhost:8080/ticket/update/{id}", ticket, Ticket.class, id);
		return "redirect:/parking/ticket/current";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable int id) {
		restTemplate.delete("http://localhost:8080/ticket/delete/{id}", id);
		return "redirect:/parking/ticket/current";
	}
}
