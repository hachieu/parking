package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.BuyMonthly;
import com.entity.Register;

@Controller
@RequestMapping("/parking/stamp")
public class BuyMonthlyController {
private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/add")
	public String showFormBuyMonthly(Model model, @RequestParam(value = "message", required = false) String message) {
		model.addAttribute("stamp", new BuyMonthly());
		model.addAttribute("message", message);
		return "buymonthly";
	}
	
	@GetMapping("/current")
	public String findAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<BuyMonthly> listBuyMonthly = Arrays.asList(restTemplate.getForObject("http://localhost:8080/stamp/current", BuyMonthly[].class));
		model.addAttribute("stamp", listBuyMonthly);
		return "listbuymonthly";
	}
	
	@PostMapping
	public String buyStamp(RedirectAttributes redirectAttributes, @RequestParam String licence) {
		LocalDate now = LocalDate.now();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String month = String.valueOf(now.getMonthValue());
		String year = String.valueOf(now.getYear());
		
		Random rd = new Random();
		String id = "VX";
		
		String nowString = String.valueOf(now);
		Date buyDate = null;
		try {
			buyDate = formatter.parse(nowString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for(int i=0;i<8;i++) {
			int value = rd.nextInt(10);
			id = id+value;
		}
		
		boolean getValue = restTemplate.getForObject("http://localhost:8080/stamp/bought/{licence}/{month}/{year}", Boolean.class, licence, month, year);
		boolean getByIdRegister = restTemplate.getForObject("http://localhost:8080/stamp/exist/{licence}", Boolean.class, licence);
		if(getByIdRegister) {
			if(!getValue) {
				Register register = restTemplate.getForObject("http://localhost:8080/register/detail/{id}", Register.class, licence);
				BuyMonthly object = new BuyMonthly(id,buyDate,register);
				restTemplate.postForObject("http://localhost:8080/stamp", object, BuyMonthly.class);
				redirectAttributes.addAttribute("message", "success");
			}else {
				redirectAttributes.addAttribute("message", "bought");
			}
		}else {
			redirectAttributes.addAttribute("message", "notexist");
		}
		
		return "redirect:/parking/stamp/add";
	}
	
//	@GetMapping("/detail/{id}")
//	public String detail(Model model, @PathVariable int id) {
//		BuyMonthly detail = restTemplate.getForObject("http://localhost:8080/stamp/detail/{id}", BuyMonthly.class, id);
//		model.addAttribute("stamp", detail);
//		return "updatebuymonthly";
//	}
	
//	@PostMapping("/update/{id}")
//	public String update(BuyMonthly buyMonthly, @PathVariable String id, RedirectAttributes redirectAttributes) {
//		buyMonthly.setId_check(Integer.parseInt(id));
//		
//		String time = buyMonthly.getDate_ticket();
//		
//		LocalDate lcd = LocalDate.parse(time);
//		
//		String month = String.valueOf(lcd.getMonthValue());
//		String year = String.valueOf(lcd.getYear());
//		int total = restTemplate.getForObject("http://localhost:8080/stamp/check/{licence}/{month}/{year}", Integer.class, buyMonthly.getLicence_plate(),month,year);
//		
//		int licence = restTemplate.getForObject("http://localhost:8080/stamp/licence/{licence}", Integer.class, buyMonthly.getLicence_plate());
//		if(licence==1) {
//			if(total<1) {
//				restTemplate.put("http://localhost:8080/stamp/update/{id}", buyMonthly, BuyMonthly.class, id);
//				redirectAttributes.addAttribute("message", "success");
//			}
//			else {
//				redirectAttributes.addAttribute("message", "failed");
//			}
//		}else {
//			redirectAttributes.addAttribute("message", "notexist");
//		}
//		
//		return "redirect:/stamp/current";
//	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id) {
		restTemplate.delete("http://localhost:8080/stamp/delete/{id}", id);
		return "redirect:/parking/stamp/current";
	}
}
