package epam.spring.core.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;

public class BirthdayDiscountStrategy implements DiscountStrategy {

	private static final double BIRTHDAY_DISCONT = 0.05;

	public BigDecimal getDiscountStrategy(User user, Event event, Date date) throws ParseException {
		if (DateConverterUtils.getBirthday(user.getBirthday()).equals(DateConverterUtils.getBirthday(date))){
			return (event.getPrice().multiply(new BigDecimal(1 - BIRTHDAY_DISCONT)));
		}
		return event.getPrice();
	}

}
