package epam.spring.core.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Auditorium {

	private String name;
	private List<String> vipSeats;
	private int seats;
	private List<Date> boookedTime;

	public Auditorium() {
	}

	public Auditorium(String name, String vipSeats, int seats) {
		this.name = name;
		this.vipSeats = Arrays.asList(vipSeats.split(","));
		this.seats = seats;
	}

	public List<String> getVipSeats() {
		return vipSeats;
	}

	public void setVipSeats(List<String> vipSeats) {
		this.vipSeats = vipSeats;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Date> getBoookedTime() {
		return boookedTime;
	}

	public void setBoookedTime(Date boookedTime) {
		this.boookedTime.add(boookedTime);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boookedTime == null) ? 0 : boookedTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((vipSeats == null) ? 0 : vipSeats.hashCode());
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
		Auditorium other = (Auditorium) obj;
		if (boookedTime == null) {
			if (other.boookedTime != null)
				return false;
		} else if (!boookedTime.equals(other.boookedTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vipSeats == null) {
			if (other.vipSeats != null)
				return false;
		} else if (!vipSeats.equals(other.vipSeats))
			return false;
		return true;
	}
}