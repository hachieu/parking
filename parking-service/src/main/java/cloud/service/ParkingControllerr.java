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

import cloud.data.ParkingRepository;
import cloud.entity.Parking;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/parking", produces = "application/json")
public class ParkingControllerr {
	@Autowired
	private ParkingRepository parkingRepository;
	
	@GetMapping("/current")
	public List<Parking> findAll(){
		return parkingRepository.findAll();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Parking postParking(@RequestBody Parking parking) {
		return parkingRepository.save(parking);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<Parking> detailParking(@PathVariable int id){
		return parkingRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public Parking putParking(@RequestBody Parking parking, @PathVariable String id) {
		return parkingRepository.save(parking);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteParking(@PathVariable int id) {
		parkingRepository.deleteById(id);
	}
	
	@GetMapping("/slot/{id}")
	public int getSlot(@PathVariable int id) {
		int slot = parkingRepository.getSlotParking(id);
		if(slot>0) {
			return slot;
		}
		return 0;
	}
	
	@GetMapping("/count/{status}/{id}")
	public int countVehicleM(@PathVariable String status,@PathVariable int id) {
		int count = parkingRepository.countVehicle(status, id);
		if(count>0) {
			return count;
		}
		return 0;
	}
}
