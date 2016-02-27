package epam.spring.core.bean;

import java.math.BigDecimal;

public class Event {

	private String name;
	private BigDecimal basePrice;
	private Raiting raiting;

	public enum Raiting {
		HIGH, MEDIUM, LOW
	}

	public Event(String name, BigDecimal price, Raiting raiting) {
		this.name = name;
		this.basePrice = price;
		this.raiting = raiting;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
	
		if (raiting != other.raiting)
			return false;
		return true;
	}
}