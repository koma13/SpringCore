package epam.spring.core.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;

public interface DiscountService {

	public BigDecimal getDiscount(User user, Event event, Date date) throws ParseException;

}
