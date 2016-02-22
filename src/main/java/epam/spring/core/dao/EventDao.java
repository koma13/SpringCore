package epam.spring.core.dao;

import java.util.List;
import java.util.Map;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;

public interface EventDao {

	public void create(Event event);

	public void remove(Event event);

	public Event getEventByName(String name);

	/*
	 * public List<Event> getForDateRange(Date fromDate, Date toDate OPTIONAL );
	 * 
	 * public List<Event> getNextEvents (Date toDate OPTIONAL);
	 */

	public List<Event> getAllEvents();

	public Map<Event, Auditorium> getEventsToAuditorium();

	public void setEventsToAuditorium(Map<Event, Auditorium> eventsToAuditorium);

	public void setAllEvents(List<Event> allEvents);

	public void assignAuditorium(Event event, Auditorium auditorium);

}
