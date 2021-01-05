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

import cloud.data.BuyMonthlyRepository;
import cloud.data.RegisterRepository;
import cloud.entity.BuyMonthly;
import cloud.entity.Register;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/stamp", produces = "application/json")
public class BuyMonthlyController {
	@Autowired
	private BuyMonthlyRepository buyMonthlyRepository;
	@Autowired
	private RegisterRepository registerRepository;
	
	@GetMapping("/current")
	public List<BuyMonthly> findAll(){
		return buyMonthlyRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public BuyMonthly buyStamp(@RequestBody BuyMonthly buyMonthly) {
		return buyMonthlyRepository.save(buyMonthly);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<BuyMonthly> detailStamp(@PathVariable String id) {
		return buyMonthlyRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public BuyMonthly putBuyMonthly(@RequestBody BuyMonthly buyMonthly) {
		return buyMonthlyRepository.save(buyMonthly);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteBuyMonthly(@PathVariable String id) {
		buyMonthlyRepository.deleteById(id);
	}
	
	@GetMapping("/bought/{licence}/{month}/{year}")
	public boolean checkBuy(@PathVariable String licence,@PathVariable String month,@PathVariable String year) {
		Optional<BuyMonthly> value = buyMonthlyRepository.checkBuy(licence, month, year);
		if(value.isPresent()) {
			return true;
		}
		return false;
	}
	
	@GetMapping("/get-stamp/{licence}/{month}/{year}")
	public Optional<BuyMonthly> getStamp(@PathVariable String licence,@PathVariable String month,@PathVariable String year){
		Optional<BuyMonthly> value = buyMonthlyRepository.getStamp(licence, month, year);
		
		if(value.isPresent()) {
			return value;
		}
		return null;
	}
	
	@GetMapping("/exist/{licence}")
	public boolean checkRegister(@PathVariable String licence) {
		Optional<Register> getValue = registerRepository.findById(licence);
		if(getValue.isPresent()) {
			return true;
		}
		return false;
	}
}
