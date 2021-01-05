package cloud.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String vehicle;
	private int price;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ticket")
	private List<Register> register;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ticketId")
	private List<CheckIn> checkIn;
	
	public Ticket() {
		super();
	}

	public Ticket(String vehicle, int price) {
		super();
		this.vehicle = vehicle;
		this.price = price;
	}

	public Ticket(int id, String vehicle, int price) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Register> getRegister() {
		return register;
	}

	public void setRegister(List<Register> register) {
		this.register = register;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
}
