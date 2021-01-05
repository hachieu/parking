package com.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/parking")
public class HomeController {
//	private RestTemplate restTemplate = new RestTemplate();
//	
//	@Autowired
//    private IAuthenticationFacade authenticationFacade;
	
	@GetMapping("/index")
	public String homePage(HttpSession session) {
//		
//		Authentication authentication = authenticationFacade.getAuthentication();
//		if(authentication!=null) {
//			Employee employee = restTemplate.getForObject("http://localhost:8080/employee/detail/{", responseType, uriVariables)
//			session.setAttribute("account", );
//		}
		return "index";
	}
}
