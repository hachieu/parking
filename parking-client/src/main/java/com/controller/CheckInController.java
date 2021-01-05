package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.configservice.IAuthenticationFacade;
import com.entity.BuyMonthly;
import com.entity.CheckIn;
import com.entity.Employee;
import com.entity.Parking;
import com.entity.Register;
import com.entity.Ticket;
import com.entity.User;

@Controller
@RequestMapping("/parking/checkin")
public class CheckInController {
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showFormCheckin(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Parking> listParking = Arrays.asList(restTemplate.getForObject("http://localhost:8080/parking/current", Parking[].class));
		model.addAttribute("parking", listParking);
		
		List<Ticket> listTicket = Arrays.asList(restTemplate.getForObject("http://localhost:8080/ticket/current", Ticket[].class));
		model.addAttribute("ticket", listTicket);
		
		model.addAttribute("message", message);
		return "checkin";
	}
	
	@PostMapping
	public String save(Model model, RedirectAttributes redirectAttributes, 
			@RequestParam String licence, @RequestParam String ticket, @RequestParam String parking) {
		int idParking = Integer.parseInt(parking);
		int idTicket = Integer.parseInt(ticket);
		
		int slot = restTemplate.getForObject("http://localhost:8080/parking/slot/{id}", Integer.class, idParking);
		int count = restTemplate.getForObject("http://localhost:8080/parking/count/{status}/{id}", Integer.class, "1", idParking);
			
		if(count<slot) {
			boolean getLicence = restTemplate.getForObject("http://localhost:8080/checkin/find-licence/{licence}/{status}", Boolean.class, licence,"1");
			if(!getLicence) {
				Parking getParking = restTemplate.getForObject("http://localhost:8080/parking/detail/{id}", Parking.class, idParking);
				LocalDate now = LocalDate.now();
				
				Random rd = new Random();
				String id = "CI";
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
				
				String time = formatter.format(new Date());
				
				Date date = null;
				
				try {
					date = formatter.parse(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				for(int i=0;i<8;i++) {
					int random = rd.nextInt(10);
					id = id+random;
				}
				
				Authentication authentication = authenticationFacade.getAuthentication();
				User user = restTemplate.getForObject("http://localhost:8080/user/find-email/{email}", User.class, authentication.getName());
				Employee getEmployee = restTemplate.getForObject("http://localhost:8080/employee/find-id/{id}", Employee.class, user.getId());
				
				String month = String.valueOf(now.getMonthValue());
				String year = String.valueOf(now.getYear());

				boolean bought = restTemplate.getForObject("http://localhost:8080/stamp/bought/{licence}/{month}/{year}", Boolean.class, licence,month,year);
				CheckIn checkIn = null;
				if(bought) {
					Register getId = restTemplate.getForObject("http://localhost:8080/register/detail/{id}", Register.class, licence);
					Ticket getTicket = restTemplate.getForObject("http://localhost:8080/ticket/detail/{id}", Ticket.class, getId.getTicket().getId());
					BuyMonthly getStam = restTemplate.getForObject("http://localhost:8080/stamp/get-stamp/{licence}/{month}/{year}", BuyMonthly.class, licence,month,year);
					
					checkIn = new CheckIn(id, licence, date, getParking, getStam, getTicket,"1", getEmployee);
				}else {
					BuyMonthly getStam = restTemplate.getForObject("http://localhost:8080/stamp/get-stamp/{licence}/{month}/{year}", BuyMonthly.class, licence,month,year);
					Ticket getTicket = restTemplate.getForObject("http://localhost:8080/ticket/detail/{id}", Ticket.class, idTicket);
					checkIn = new CheckIn(id, licence, date, getParking, getStam , getTicket,"1", getEmployee);
				}
				
				restTemplate.postForObject("http://localhost:8080/checkin", checkIn, CheckIn.class);
				redirectAttributes.addAttribute("message", "success");
				
			}else {
				redirectAttributes.addAttribute("message", "exist");
			}
		}else {
			redirectAttributes.addAttribute("message", "slot");
		}
		
		return "redirect:/parking/checkin/add";
	}
	
	@GetMapping("/current")
	public String findAll(Model model) {
		List<CheckIn> listCheckIn = Arrays.asList(restTemplate.getForObject("http://localhost:8080/checkin/current", CheckIn[].class));
		model.addAttribute("checkin", listCheckIn);
		return "listcheckin";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id) {
		restTemplate.delete("http://localhost:8080/checkin/delete/{id}", id);
		return "redirect:/parking/checkin/current";
	}
}
