package epam.spring.core.bean;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class User {

	public final static Logger logger = Logger.getLogger(User.class);

	private long id;
	private String fullName;
	private Date birthday;
	private String email;
	private List<Ticket> bookedTickets;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Ticket> getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(List<Ticket> bookedTickets) {
		this.bookedTickets = bookedTickets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((bookedTickets == null) ? 0 : bookedTickets.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (bookedTickets == null) {
			if (other.bookedTickets != null)
				return false;
		} else if (!bookedTickets.equals(other.bookedTickets))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
