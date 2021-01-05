package cloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cloud.data.CheckOutRepository;
import cloud.entity.CheckOut;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/checkout", produces = "application/json")
public class CheckOutController {
	@Autowired
	private CheckOutRepository checkOutRepository;
	
	@GetMapping("/current")
	public List<CheckOut> findAll(){
		return checkOutRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckOut save(@RequestBody CheckOut checkOut) {
		return checkOutRepository.save(checkOut);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String id) {
		checkOutRepository.deleteById(id);
	}
}
