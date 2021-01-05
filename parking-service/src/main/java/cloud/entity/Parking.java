package cloud.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "parking")
public class Parking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Nationalized
	private String parkingName;
	private int area;
	private int slot;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parkingId")
	private List<CheckIn> checkIn;

	public Parking() {
		super();
	}

	public Parking(String parkingName, int area, int slot) {
		super();
		this.parkingName = parkingName;
		this.area = area;
		this.slot = slot;
	}

	public Parking(int id, String parkingName, int area, int slot) {
		super();
		this.id = id;
		this.parkingName = parkingName;
		this.area = area;
		this.slot = slot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
}
