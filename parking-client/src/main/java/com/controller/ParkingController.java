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

import com.entity.Parking;

@Controller
@RequestMapping("/parking/park")
public class ParkingController {
private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showFormParking(Model model, @RequestParam(value = "message", required = false) String message) {
		model.addAttribute("parking", new Parking());
		model.addAttribute("message", message);
		return "parking";
	}
	
	@GetMapping("/current")
	public String listParking(Model model) {
		List<Parking> listParking = Arrays.asList(restTemplate.getForObject("http://localhost:8080/parking/current", Parking[].class));
		model.addAttribute("parking", listParking);
		return "listparking";
	}
	
	@PostMapping
	public String postParking(Parking parking, RedirectAttributes redirectAttributes) {
		restTemplate.postForObject("http://localhost:8080/parking", parking, Parking.class);
		redirectAttributes.addAttribute("message", "success");
		return "redirect:/parking/park/add";
	}
	
	@GetMapping("/detail/{id}")
	public String detailParking(Model model, @PathVariable int id) {
		Parking detail = restTemplate.getForObject("http://localhost:8080/parking/detail/{id}", Parking.class, id);
		model.addAttribute("parking", detail);
		return "updateparking";
	}
	
	@PostMapping("/update/{id}")
	public String updateParking(Parking parking, @PathVariable String id) {
		parking.setId(Integer.parseInt(id));
		restTemplate.put("http://localhost:8080/parking/update/{id}", parking, Parking.class, id);
		return "redirect:/parking/park/current";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteParking(@PathVariable String id) {
		restTemplate.delete("http://localhost:8080/parking/delete/{id}", id);
		return "redirect:/parking/park/current";
	}
}
