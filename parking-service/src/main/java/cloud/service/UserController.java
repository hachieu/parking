package cloud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cloud.data.UserRepository;
import cloud.entity.User;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user", produces = "application/json")
public class UserController {
	@Autowired
	private UserRepository userReplository;
	
	@GetMapping("/current")
	public List<User> findAll(){
		return userReplository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody User user) {
		return userReplository.save(user);
	}
	
	@GetMapping("/find-email/{email}")
	public User findByEmail(@PathVariable String email) {
		return userReplository.findByEmail(email);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<User> findById(@PathVariable int id) {
		return userReplository.findById(id);
	}
	
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		userReplository.deleteById(id);
	}
	
	@GetMapping("/find-active/{email}")
	public User findByActive(@PathVariable String email) {
		return userReplository.findByActive(email);
	}
	
	@GetMapping("/check-email/{email}")
	public boolean checkEmail(@PathVariable String email) {
		User user = userReplository.findByActive(email);
		
		if(user!=null) {
			return true;
		}
		return false;
	}
	
	@PutMapping("/active/{id}")
	public User active(@RequestBody User user) {
		return userReplository.save(user);
	}
}
