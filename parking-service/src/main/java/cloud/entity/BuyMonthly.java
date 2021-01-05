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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "buy_monthly_ticket")
public class BuyMonthly {
	@Id
	private String stamp;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date buyDate;
	
	@ManyToOne
    @JoinColumn(name = "licence_plate")
	private Register register;
	
	@JsonIgnore
	@OneToMany(mappedBy = "buyStamp")
	private List<CheckIn> checkIn;
	
	public BuyMonthly() {
		super();
	
	}

	public BuyMonthly(String stamp, Date buyDate, Register register) {
		super();
		this.stamp = stamp;
		this.buyDate = buyDate;
		this.register = register;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public List<CheckIn> getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(List<CheckIn> checkIn) {
		this.checkIn = checkIn;
	}
}
