package cloud.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CheckOut {
	@Id
	private String id;
	
	@DateTimeFormat(pattern = "yyyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOut;

	private int price;
	
	@OneToOne
	@JoinColumn(name = "checkIn_id")
	private CheckIn checkIn;
	
	@ManyToOne
	@JoinColumn( name = "employee_id")
	private Employee employee;

	public CheckOut() {
		super();
	}

	public CheckOut(String id, Date dateOut, int price, CheckIn checkIn, Employee employee) {
		super();
		this.id = id;
		this.dateOut = dateOut;
		this.price = price;
		this.checkIn = checkIn;
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
