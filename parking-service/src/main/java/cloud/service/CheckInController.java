package cloud.service;

import java.util.List;

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

import cloud.data.CheckInRepository;
import cloud.entity.CheckIn;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/checkin", produces = "application/json")
public class CheckInController {
	@Autowired
	private CheckInRepository checkInRepository;
	
	@GetMapping("/current")
	public List<CheckIn> findAll(){
		return checkInRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckIn save(@RequestBody CheckIn checkIn) {
		return checkInRepository.save(checkIn);
	}
	
	@DeleteMapping("/delete/{id}") 
	public void delete(@PathVariable String id) {
		checkInRepository.deleteById(id);
	}
	
	@GetMapping("/find-licence/{licence}/{status}")
	public boolean findLicence(@PathVariable String licence,@PathVariable String status) {
		CheckIn getLicence = checkInRepository.checkLicencePlate(licence, status);
		
		if(getLicence!=null) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/get-licence/{licence}/{status}")
	public CheckIn getLicence(@PathVariable String licence,@PathVariable String status) {
		CheckIn getLicence = checkInRepository.checkLicencePlate(licence, status);
		
		if(getLicence!=null) {
			return getLicence;
		}
		return null;
	}
	
	@PutMapping("/update/{id}")
	public CheckIn update(@RequestBody CheckIn checkIn) {
		return checkInRepository.save(checkIn);
	}
}
