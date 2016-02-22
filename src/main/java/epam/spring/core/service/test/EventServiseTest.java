package epam.spring.core.service.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;
import epam.spring.core.dao.AuditoriumDao;
import epam.spring.core.dao.EventDao;
import epam.spring.core.dao.impl.EventDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class EventServiseTest {

	@Autowired
	private EventDao eventDao;

	@Autowired
	private AuditoriumDao auditoriumDao;

	@Test
	public void testCreateEvent() {
		Event event = EventDaoImpl.event1;
		eventDao.create(event);
		assertTrue("Event was not created", eventDao.getAllEvents().contains(event));
	}

	@Test
	public void testAssignAuditorium() {
		Auditorium auditorium = auditoriumDao.getAuditoriums().get(1);
		Event event = EventDaoImpl.event1;
		eventDao.assignAuditorium(event, auditorium);
		assertTrue("Event was not assigned to auditorium", eventDao.getEventsToAuditorium().containsKey(event));
	}
}
