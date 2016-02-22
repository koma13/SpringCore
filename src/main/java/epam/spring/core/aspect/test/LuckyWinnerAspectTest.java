package epam.spring.core.aspect.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class LuckyWinnerAspectTest {

	@Autowired
	BookingService bookingService;

	@Test
	public void testEventAccessByName() {
		User user = UserDaoImpl.user;
		Ticket ticket = UserDaoImpl.ticket;
		bookingService.bookTicket(user, ticket);
	}

}
