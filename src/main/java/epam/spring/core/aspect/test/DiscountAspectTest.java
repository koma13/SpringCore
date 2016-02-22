package epam.spring.core.aspect.test;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.aspect.DiscountAspect;
import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.DiscountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class DiscountAspectTest {
	
	@Autowired
	DiscountService discountService;
	
	@Test
	public void testEventAccessByName() throws ParseException {
		User user = UserDaoImpl.user;
		Date date = UserDaoImpl.date;
		Event event = EventDaoImpl.event1;
		discountService.getDiscount(user, event, date);
		assertTrue("AspectDiscount was not called", DiscountAspect.discountByUserCounter.size() > 0);
	}
}
