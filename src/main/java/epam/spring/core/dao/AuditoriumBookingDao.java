package epam.spring.core.dao;

import java.util.Date;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;

public interface AuditoriumBookingDao {

	public void assignAuditorium(Event event, Auditorium auditorium, Date date);

	public void setBookedTicketsByEvent(String eventName, Date date, int ticketsToBook);

	public int getBookedTicketsByEvent(String eventname, Date date);

	void removeDataFromTable();

}
