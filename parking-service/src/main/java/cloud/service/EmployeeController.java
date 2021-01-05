package cloud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cloud.data.EmployeeRepository;
import cloud.entity.Employee;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/employee", produces = "application/json")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/current")
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee postEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<Employee> detailEmployee(@PathVariable String id) {
		return employeeRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public Employee putEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable String id) {
		employeeRepository.deleteById(id);
	}
	
	@GetMapping("/identity/{value}")
	public boolean findIdentity(@PathVariable String value) {
		String getValue = employeeRepository.checkIdentity(value);
		if(getValue!=null) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/phone/{value}")
	public boolean findPhone(@PathVariable String value) {
		String getValue = employeeRepository.checkPhone(value);
		if(getValue!=null) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/find-id/{id}")
	public Employee findByUser(@PathVariable String id) {
		return employeeRepository.findByUser(id);
	}
}
