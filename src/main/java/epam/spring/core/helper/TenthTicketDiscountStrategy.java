package epam.spring.core.helper;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.TicketBookingDao;

public class TenthTicketDiscountStrategy implements DiscountStrategy {
	
	private TicketBookingDao ticketBooking;

	@Autowired
	public void setTicketBookingDao(TicketBookingDao ticketBookingDao) {
		this.ticketBooking = ticketBookingDao;
	}

	public static double getThenthTicketDiscont() {
		return THENTH_TICKET_DISCONT;
	}

	private static final double THENTH_TICKET_DISCONT = 0.5;

	public BigDecimal getDiscountStrategy(User user, Event event, Date date) {
		int purchasedTicketAmount = ticketBooking.getBookedTicketsAmountByUser(user);
		if (purchasedTicketAmount % 10 == 0)
			return event.getPrice().multiply(new BigDecimal(1 - THENTH_TICKET_DISCONT));
		return event.getPrice();
	}

}
