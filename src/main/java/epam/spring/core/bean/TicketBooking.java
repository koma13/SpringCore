package epam.spring.core.bean;

public class TicketBooking {

	private int userId;
	private int ticketsToBook;
	private String eventName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTicketsToBook() {
		return ticketsToBook;
	}

	public void setTicketsToBook(int ticketsToBook) {
		this.ticketsToBook = ticketsToBook;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "TicketBooking [userId=" + userId + ", ticketsToBook=" + ticketsToBook + ", eventName=" + eventName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ticketsToBook;
		result = prime * result + userId;
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
		TicketBooking other = (TicketBooking) obj;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (ticketsToBook != other.ticketsToBook)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
