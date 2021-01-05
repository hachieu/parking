package cloud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cloud.data.RoleRepository;
import cloud.entity.Role;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/role", produces = "application/json")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/current")
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Role save(@RequestBody Role role) {
		return roleRepository.save(role);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<Role> detail(@PathVariable int id){
		return roleRepository.findById(id);
	}
	
	@GetMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		roleRepository.deleteById(id);
	}
}
