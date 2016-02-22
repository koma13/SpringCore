package epam.spring.core.helper;

import java.math.BigDecimal;
import java.util.Date;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;

public class TenthTicketDiscountStrategy implements DiscountStrategy {

	private static final double THENTH_TICKET_DISCONT = 0.5;

	public BigDecimal getDiscountStrategy(User user, Event event, Date date) {
		int purchasedTicketAmount = user.getBookedTickets().size();
		if (purchasedTicketAmount % 10 == 0)
			return event.getPrice().multiply(new BigDecimal(1 - THENTH_TICKET_DISCONT));
		return event.getPrice();
	}

}
