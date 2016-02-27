package epam.spring.core.bean;

public class AspectCounter {

	private String eventName;
	private int counterAccesseventByName;
	private int counterEventPrice;
	private int counterTicketBooking;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getCounterAccesseventByName() {
		return counterAccesseventByName;
	}

	public void setCounterAccesseventByName(int counterAccesseventByName) {
		this.counterAccesseventByName = counterAccesseventByName;
	}

	public int getCounterEventPrice() {
		return counterEventPrice;
	}

	public void setCounterEventPrice(int counterEventPrice) {
		this.counterEventPrice = counterEventPrice;
	}

	public int getCounterTicketBooking() {
		return counterTicketBooking;
	}

	public void setCounterTicketBooking(int counterTicketBooking) {
		this.counterTicketBooking = counterTicketBooking;
	}

	@Override
	public String toString() {
		return "AspectCounter [eventName=" + eventName + ", counterAccesseventByName=" + counterAccesseventByName + ", counterEventPrice=" + counterEventPrice + ", counterTicketBooking="
				+ counterTicketBooking + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + counterAccesseventByName;
		result = prime * result + counterEventPrice;
		result = prime * result + counterTicketBooking;
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
		AspectCounter other = (AspectCounter) obj;
		if (counterAccesseventByName != other.counterAccesseventByName)
			return false;
		if (counterEventPrice != other.counterEventPrice)
			return false;
		if (counterTicketBooking != other.counterTicketBooking)
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		return true;
	}

}
