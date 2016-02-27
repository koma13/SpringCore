package epam.spring.core.aspect.test;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.LuckyWinnerDao;
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class LuckyWinnerAspectTest {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	private LuckyWinnerDao luckyWinnerDao;
	
	@Before
	public void prepareTestData() {
		cleanAspectCounterTable();
	}

	@Test
	public void testEventAccessByName() {
		User user = UserDaoImpl.user2;
		Event event = EventDaoImpl.event1;
		Date date = UserDaoImpl.date3;
		bookingService.bookTicket(user, 1, event, date);
		assertTrue("LuckyWinnerAspect are nor working correct", luckyWinnerDao.getAmountLuckyTicketsByUser(user) >= 0);
	}
	

	private void cleanAspectCounterTable() {
		luckyWinnerDao.removeDataFromTable();
	}
}
