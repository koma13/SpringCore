package epam.spring.core.aspect.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.AspectCounter;
import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.AspectCounterDao;
import epam.spring.core.dao.EventDao;
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class CounterAspectTest {

	private static final boolean IS_VIP_SEAT = true;

	private static final int AMOUNT_OF_SEATS = 2;
	private static final int ZERO = 0;

	@Autowired
	EventDao eventDao;

	@Autowired
	BookingService bookingService;

	@Autowired
	AspectCounterDao aspectCounterDao;

	@Before
	public void prepareTestData() {
		cleanAspectCounterTable();
	}

	@Test
	public void testEventAccessByName() {
		Event event = EventDaoImpl.event2;
		eventDao.create(event);
		Event event2 = eventDao.getEventByName(event.getName());
		AspectCounter eventAccessInfo = aspectCounterDao.getAspectCount(event2.getName());
		System.out.println(eventAccessInfo.toString());
		assertTrue("AspectCounter was not called", eventAccessInfo.getCounterAccesseventByName() > ZERO);
	}

	@Test
	public void testEventPriceQueried() throws ParseException {
		User user = UserDaoImpl.user1;
		Event event = EventDaoImpl.event2;
		eventDao.create(event);
		Date date = UserDaoImpl.date1;
		bookingService.getTicketPrice(event, date, AMOUNT_OF_SEATS, user, IS_VIP_SEAT);
		AspectCounter eventAccessInfo = aspectCounterDao.getAspectCount(event.getName());
		System.out.println(eventAccessInfo.toString());
		assertTrue("AspectCounter was not called", eventAccessInfo.getCounterEventPrice() > ZERO);
	}

	@Test
	public void testCounterForBookedTicketsByEvent() throws ParseException {
		User user = UserDaoImpl.user1;
		Date date = UserDaoImpl.date1;
		Event event = EventDaoImpl.event2;
		bookingService.bookTicket(user, 2, event, date);
		String eventName = event.getName();
		AspectCounter eventAccessInfo = aspectCounterDao.getAspectCount(eventName);
		System.out.println(eventAccessInfo.toString());
		assertTrue("AspectCounter was not called", eventAccessInfo.getCounterTicketBooking() > ZERO);
	}

	private void cleanAspectCounterTable() {
		aspectCounterDao.removeDataFromTable();
	}

}
