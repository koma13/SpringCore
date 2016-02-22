package epam.spring.core.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;

public interface DiscountStrategy {

	public BigDecimal getDiscountStrategy(User user, Event event, Date date) throws ParseException;

}
