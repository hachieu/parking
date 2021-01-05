package cloud.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	private String id;
	@Nationalized
	private String fullName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	@Nationalized
	private String gender;
	private String identity;
	private String phone;
	@Nationalized
	private String address;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<CheckIn> checkIn;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<CheckOut> checkOut;

	public Employee() {
		super();
	}

	public Employee(String id, String fullName, Date birthDate, String gender, String identity, String phone,
			String address, User user) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.identity = identity;
		this.phone = phone;
		this.address = address;
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}

	public List<CheckOut> getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(List<CheckOut> checkOut) {
		this.checkOut = checkOut;
	}
}
