package cloud.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="register_monthly_ticket")
public class Register {
	@Id
	private String licencePlate;
	@Nationalized
	private String nameCustomer;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dateIssue;
	
	@ManyToOne
    @JoinColumn(name = "type_ticket")
	private Ticket ticket;
	
	@JsonIgnore
	@OneToMany(mappedBy = "register")
	private List<BuyMonthly> buyMonthly;
	
	public Register() {
		super();
	}

	public Register(String licencePlate, String nameCustomer, Date birthDate, Date dateIssue, Ticket ticket) {
		super();
		this.licencePlate = licencePlate;
		this.nameCustomer = nameCustomer;
		this.birthDate = birthDate;
		this.dateIssue = dateIssue;
		this.ticket = ticket;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(Date dateIssue) {
		this.dateIssue = dateIssue;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public List<BuyMonthly> getBuyMonthly() {
		return buyMonthly;
	}

	public void setBuyMonthly(List<BuyMonthly> buyMonthly) {
		this.buyMonthly = buyMonthly;
	}
}
