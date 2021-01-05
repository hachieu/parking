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

import cloud.data.TicketRepository;
import cloud.entity.Ticket;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ticket", produces = "application/json")
public class TicketController {
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping("/current")
	public List<Ticket> findAll(){
		return ticketRepository.findAll();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket postTicket(@RequestBody Ticket ticket) {
		return ticketRepository.save(ticket);
	}
	
	@GetMapping("/detail/{id}")
	public Optional<Ticket> detailTicket(@PathVariable int id){
		return ticketRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public Ticket putTicket(@RequestBody Ticket ticket, @PathVariable String id) {
		return ticketRepository.save(ticket);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTicket(@PathVariable int id) {
		ticketRepository.deleteById(id);
	}
}
