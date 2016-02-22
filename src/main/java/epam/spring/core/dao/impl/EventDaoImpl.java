package epam.spring.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;
import epam.spring.core.dao.EventDao;

public class EventDaoImpl implements EventDao {

	public static List<Event> allEvents;
	private Map<Event, Auditorium> eventsToAuditorium;
	public static Event event1;
	public static Event event2;

	public Map<Event, Auditorium> getEventsToAuditorium() {
		return eventsToAuditorium;
	}

	public void setEventsToAuditorium(Map<Event, Auditorium> eventsToAuditorium) {
		this.eventsToAuditorium = eventsToAuditorium;
	}

	public void setAllEvents(List<Event> allEvents) {
		EventDaoImpl.allEvents = allEvents;
	}

	static {
		event1 = new Event("Concert OE", new BigDecimal(500), Event.Raiting.LOW);
		event2 = new Event("Film XX", new BigDecimal(56), Event.Raiting.HIGH);

	}

	public void create(Event event) {
		if (!allEvents.contains(event))
			allEvents.add(event);
	}

	public void remove(Event event) {
		if (allEvents.contains(event))
			allEvents.remove(event);
	}

	public Event getEventByName(String name) {
		Event newEvent = new Event();
		for (Event event : allEvents)
			if (event.getName().equals(name))
				return event;

		return newEvent;
	}

	public List<Event> getAllEvents() {
		return allEvents;
	}

	public void assignAuditorium(Event event, Auditorium auditorium) {
		eventsToAuditorium.put(event, auditorium);
	}

}
