package epam.spring.core.dao;

import java.util.List;

import epam.spring.core.bean.Auditorium;

public interface AuditoriumDao {

	public List<Auditorium> getAuditoriums();

	public List<String> getVipSeats(String auditoriumName) throws Exception;

	public int getSeatsNumber(String auditoriumName) throws Exception;

}
