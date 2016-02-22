package epam.spring.core.aspect.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.aspect.CounterAspect;
import epam.spring.core.bean.Event;
import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;
import epam.spring.core.dao.EventDao;
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class CounterAspectTest {

	private static final boolean IS_VIP_SEAT = true;

	private static final int AMOUNT_OF_SEATS = 2;

	@Autowired
	EventDao eventDao;

	@Autowired
	BookingService bookingService;

	@Test
	public void testEventAccessByName() {
		Event event = EventDaoImpl.event1;
		eventDao.getEventByName(event.getName());
		int ZERO = 0;
		assertTrue("AspectCounter was not called", CounterAspect.eventAccessCounter.size() > ZERO);
	}

	@Test
	public void testEventPriceQueried() throws ParseException {
		User user = UserDaoImpl.user;
		Event event = EventDaoImpl.event1;
		Date date = UserDaoImpl.date;
		bookingService.getTicketPrice(event, date, AMOUNT_OF_SEATS, user, IS_VIP_SEAT);
		assertTrue("AspectCounter was not called", CounterAspect.eventPriceAccessCounter.size() > 0);
	}

	@Test
	public void testCounterForBookedTicketsByEvent() throws ParseException {
		User user = UserDaoImpl.user;
		Ticket ticket = UserDaoImpl.ticket;
		bookingService.bookTicket(user, ticket);
		String eventName = ticket.getEvent().getName();
		assertTrue("AspectCounter was not called", CounterAspect.eventTicketBookingCounter.containsKey(eventName));
	}

}
