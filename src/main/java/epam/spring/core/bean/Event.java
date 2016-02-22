package epam.spring.core.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Event {

	private String name;
	private BigDecimal basePrice;
	private Raiting raiting;

	private Map<Date, Integer> purchasedTickets;
	private Map<Date, Auditorium> eventsByDate;

	public enum Raiting {
		HIGH, MEDIUM, LOW
	}

	public Event(String name, BigDecimal price, Raiting raiting) {
		this.name = name;
		this.basePrice = price;
		this.raiting = raiting;
		purchasedTickets = new HashMap<Date, Integer>();
		eventsByDate = new HashMap<Date, Auditorium>();

	}

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return basePrice;
	}

	public void setPrice(BigDecimal price) {
		this.basePrice = price;
	}

	public Raiting getRaiting() {
		return raiting;
	}

	public void setRaiting(Raiting raiting) {
		this.raiting = raiting;
	}

	public Map<Date, Integer> getPurchasedTickets() {
		return purchasedTickets;
	}

	public void setPurchasedTickets(Map<Date, Integer> purchasedTickets) {
		this.purchasedTickets = purchasedTickets;
	}

	public Map<Date, Auditorium> getEventsByDate(Date date) {
		Map<Date, Auditorium> eventByDate = new HashMap<Date, Auditorium>();
		for (Map.Entry<Date, Auditorium> entry : eventsByDate.entrySet())
			if (entry.getKey().equals(date))
				eventByDate.put(entry.getKey(), entry.getValue());
		return eventByDate;
	}

	public void setEventsByDate(Map<Date, Auditorium> event) {
		this.eventsByDate.putAll(event);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((eventsByDate == null) ? 0 : eventsByDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((purchasedTickets == null) ? 0 : purchasedTickets.hashCode());
		result = prime * result + ((raiting == null) ? 0 : raiting.hashCode());
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
		Event other = (Event) obj;
		if (basePrice == null) {
			if (other.basePrice != null)
				return false;
		} else if (!basePrice.equals(other.basePrice))
			return false;
		if (eventsByDate == null) {
			if (other.eventsByDate != null)
				return false;
		} else if (!eventsByDate.equals(other.eventsByDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (purchasedTickets == null) {
			if (other.purchasedTickets != null)
				return false;
		} else if (!purchasedTickets.equals(other.purchasedTickets))
			return false;
		if (raiting != other.raiting)
			return false;
		return true;
	}
}