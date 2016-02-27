package epam.spring.core.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.Event;
import epam.spring.core.dao.EventDao;
import epam.spring.core.dao.impl.EventDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class EventDaoTest {

	@Autowired
	EventDao eventDao;

	Event event;
	Event eventToRemove;
	Event eventToCreate;

	@Before
	public void prepareTestData() {
		cleanEventTable();

		event = EventDaoImpl.event1;
		eventDao.create(event);
		eventToRemove = EventDaoImpl.event3;
		eventDao.create(eventToRemove);

	}

	@Test
	public void testCreateEvent() throws ParseException {
		Event newEvent = EventDaoImpl.event2;
		eventDao.create(newEvent);
		Event Event = eventDao.getEventByName(newEvent.getName());
		assertEquals("Event name is not as expected", newEvent.getName(), Event.getName());
	}

	@Test
	public void testGetEventByName() throws ParseException {
		Event Event = eventDao.getEventByName(event.getName());
		assertEquals("Event name is not as expected", event.getName(), Event.getName());
	}

	@Test
	public void testGetRegisteredEvents() throws ParseException {

		List<Event> Events = eventDao.getAllEvents();
		assertNotNull("Event name is not as expected", Events.size());

	}

	@Test
	public void testRemoveEvent() throws ParseException {
		eventDao.remove(eventToRemove);
		assertNull(eventDao.getEventByName(eventToRemove.getName()));
	}

	private void cleanEventTable() {
		List<Event> events = eventDao.getAllEvents();
		if (events != null) {
			for (Event event : events) {
				eventDao.remove(event);
			}
		}
	}
}
