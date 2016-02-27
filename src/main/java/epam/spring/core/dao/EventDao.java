package epam.spring.core.dao;

import java.util.List;

import epam.spring.core.bean.Event;

public interface EventDao {

	public void create(Event event);

	public void remove(Event event);

	public Event getEventByName(String name);

	public List<Event> getAllEvents();

}
