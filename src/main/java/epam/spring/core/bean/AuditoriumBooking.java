package epam.spring.core.bean;

import java.util.Date;

public class AuditoriumBooking {
	
	private String eventName;
	private String auditoriumName;
	private Date bookedTime;
	private int bookedTickets;
	

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getAuditoriumName() {
		return auditoriumName;
	}
	public void setAuditoriumName(String auditoriumName) {
		this.auditoriumName = auditoriumName;
	}
	public Date getBookedTime() {
		return bookedTime;
	}
	public void setBookedTime(Date bookedTime) {
		this.bookedTime = bookedTime;
	}
	public int getBookedTickets() {
		return bookedTickets;
	}
	public void setBookedTickets(int bookedTickets) {
		this.bookedTickets = bookedTickets;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auditoriumName == null) ? 0 : auditoriumName.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
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
		AuditoriumBooking other = (AuditoriumBooking) obj;
		if (auditoriumName == null) {
			if (other.auditoriumName != null)
				return false;
		} else if (!auditoriumName.equals(other.auditoriumName))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		return true;
	}

}
