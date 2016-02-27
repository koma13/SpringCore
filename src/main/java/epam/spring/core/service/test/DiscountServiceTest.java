package epam.spring.core.service.test;

import static org.junit.Assert.assertTrue;

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
import epam.spring.core.dao.impl.EventDaoImpl;
import epam.spring.core.dao.impl.UserDaoImpl;
import epam.spring.core.service.DiscountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:discount_strategies.xml")
public class DiscountServiceTest {

	private DiscountService discountService;

	@Test
	public void testGetDiscountForBirthday() throws ParseException {
		Date date = UserDaoImpl.date1;
		User user = UserDaoImpl.user1;
		Event event = EventDaoImpl.event1;
		BigDecimal priceWithDiscount = discountService.getDiscount(user, event, date);
		boolean isPriceWithDiscount = event.getPrice().compareTo(priceWithDiscount) > 0;
		assertTrue("The price shoud be lower for birthday", isPriceWithDiscount);
	}

	@Autowired
	public void setDiscountService(DiscountService discountService) {
		this.discountService = discountService;
	}
}
