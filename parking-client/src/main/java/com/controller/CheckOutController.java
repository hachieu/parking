package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.entity.CheckIn;
import com.entity.CheckOut;
import com.entity.Employee;
import com.entity.User;

@Controller
@RequestMapping("/parking/checkout")
public class CheckOutController {
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showForm(Model model, @RequestParam(value = "message", required = false) String message) {
		model.addAttribute("message", message);
		return "checkout";
	}
	
	@GetMapping("/current")
	public String findAll(Model model) {
		List<CheckOut> checkOut = Arrays.asList(restTemplate.getForObject("http://localhost:8080/checkout/current", CheckOut[].class));
		model.addAttribute("checkout", checkOut);
		return "listcheckout";
	}
	
	@PostMapping
	public String save(RedirectAttributes redirectAttribute, @RequestParam String licence, Model model) {
		boolean getLicence = restTemplate.getForObject("http://localhost:8080/checkin/find-licence/{licence}/{status}", Boolean.class, licence,"1");
		if(getLicence) {
//			LocalDate now = LocalDate.now();
			
			Random rd = new Random();
			String id = "CO";
			
			
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
			
			CheckIn checkIn =  restTemplate.getForObject("http://localhost:8080/checkin/get-licence/{licence}/{status}", CheckIn.class, licence,"1");
			int price = 0;
			if(checkIn.getBuyStamp()==null) {
				price = checkIn.getTicketId().getPrice();
			}else {
				price = 0;
			}
			
			CheckOut checkOut = new CheckOut(id, date, price, checkIn, getEmployee);
			CheckIn update = new CheckIn(checkIn.getId(),checkIn.getLicencePlate(), checkIn.getDateIn(),checkIn.getParkingId(),
					checkIn.getBuyStamp(), checkIn.getTicketId(), "0", checkIn.getEmployee());
			restTemplate.put("http://localhost:8080/checkin/update/{id}", update, checkIn.getId());
			restTemplate.postForObject("http://localhost:8080/checkout", checkOut, CheckOut.class);
			model.addAttribute("message", "success");
			model.addAttribute("price", checkOut.getPrice());
			
		}else {
			model.addAttribute("message", "exist");
		}
		return "checkout";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id) {
		restTemplate.delete("http://localhost:8080/checkout/delete/{id}", id);
		return "redirect:/parking/checkout/current";
	}
}
