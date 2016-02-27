package epam.spring.core.aspect.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.AspectDiscountDao;
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.DiscountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:discount_strategies.xml")
public class DiscountAspectTest {

	@Autowired
	DiscountService discountService;

	@Autowired
	AspectDiscountDao aspectDiscountDao;

	@Before
	public void prepareTestData() {
		cleanAspectCounterTable();
	}

	@Test
	public void testGetDiscountCount() throws ParseException {
		User user = UserDaoImpl.user1;
		Date date = UserDaoImpl.date1;
		Event event2 = EventDaoImpl.event2;
		Event event = EventDaoImpl.event1;
		discountService.getDiscount(user, event, date);
		discountService.getDiscount(user, event2, date);
		assertEquals("AspectDiscount was not called", 2, aspectDiscountDao.getDiscountCountByUser(user));
	}

	private void cleanAspectCounterTable() {
		aspectDiscountDao.removeDataFromTable();
	}
}
