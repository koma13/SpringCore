package epam.spring.core.service.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.TicketBookingDao;
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:discount_strategies.xml")
public class BookingServiceTest {

	private static final boolean IS_VIP_SEAT = true;
	private static final BigDecimal EXPECTED_PRICE = new BigDecimal(2000);
	private static final int SEATS_AMOUNT = 2;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private TicketBookingDao ticketBookingDao;

	@Test
	public void testGetTicketPrice() throws ParseException {
		Event event = EventDaoImpl.event1;
		Date date = UserDaoImpl.date1;
		User user = UserDaoImpl.user1;
		BigDecimal price = bookingService.getTicketPrice(event, date, SEATS_AMOUNT, user, IS_VIP_SEAT);
		assertEquals("The amount of tickets should be more that one", EXPECTED_PRICE, price);
	}

	@Test
	public void testBookedTicketsListIsNotEmpty() {
		Event event = EventDaoImpl.event2;
		User user = UserDaoImpl.user1;
		Date date = UserDaoImpl.date1;
		bookingService.bookTicket(user, 2, event, date);
		assertEquals("The amount of tickets should be more that one", 2, ticketBookingDao.getBookedTicketsAmountByUser(user));
	}

}
