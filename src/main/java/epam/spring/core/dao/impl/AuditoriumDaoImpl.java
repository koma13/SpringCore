package epam.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.dao.AuditoriumDao;

public class AuditoriumDaoImpl implements AuditoriumDao {

	private List<Auditorium> auditoriums;

	public List<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(List<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	public int getSeatsNumber(String auditoriumName) throws Exception {
		for (Auditorium auditorium : auditoriums)
			if (auditorium.getName().equals(auditoriumName))
				return Integer.valueOf(auditorium.getSeats());

		return 0;
	}

	public List<String> getVipSeats(String auditoriumName) throws Exception {
		for (Auditorium auditorium : auditoriums) {
			if (auditorium.getName().equals(auditoriumName))
				return auditorium.getVipSeats();
		}
		return new ArrayList<String>();
	}
}
