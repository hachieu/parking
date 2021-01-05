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

import cloud.data.RegisterRepository;
import cloud.entity.Register;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/register", produces = "application/json")
public class RegisterController {
	@Autowired
	private RegisterRepository registerRepository;
	
	@GetMapping("/current")
	public List<Register> findAll(){
		return registerRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Register postRegister(@RequestBody Register register) {
		return registerRepository.save(register);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<Register> detailRegister(@PathVariable String id){
		return registerRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public Register putRegister(@RequestBody Register register) {
		return registerRepository.save(register);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRegister(@PathVariable String id) {
		registerRepository.deleteById(id);
	}
	
	@GetMapping("/isempty/{id}")
	public boolean isEmptyRegister(@PathVariable String id) {
		Optional<Register> value = registerRepository.findById(id);
		if(value.isPresent()) {
			return true;
		}
		return false;
	}
}
