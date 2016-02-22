package epam.spring.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;
import epam.spring.core.dao.UserDao;
import epam.spring.core.service.BookingService;

@Component
public class BookingServiceImpl implements BookingService {

	private static final int FIRST_ELEMENT = 1;

	private static final int VIP_SEAT_RAIT = 2;

	@Autowired
	private UserDao userDao;

	public BigDecimal getTicketPrice(Event event, Date date, int seats, User user, boolean isVipSeat) {
		BigDecimal ticketPrice = event.getPrice();
		if (isVipSeat)
			ticketPrice = ticketPrice.multiply(new BigDecimal(VIP_SEAT_RAIT));
		ticketPrice = ticketPrice.multiply(new BigDecimal(seats));
		return ticketPrice;
	}

	public void bookTicket(User user, Ticket ticket) {
		String email = user.getEmail();
		if (isUserRegistered(email))
			user.getBookedTickets().add(ticket);
		Map<Date, Integer> purchasedTickets = ticket.getEvent().getPurchasedTickets();
		Date dateKey = ticket.getDate();
		if (purchasedTickets.containsKey(dateKey)) {
			Integer tickets = purchasedTickets.get(dateKey);
			if (tickets != null)
				purchasedTickets.put(dateKey, ++tickets);
		} else
			purchasedTickets.put(dateKey, FIRST_ELEMENT);
	}

	public int getTicketsForEvent(Event event, Date date) {
		return event.getPurchasedTickets().get(date);
	}

	private boolean isUserRegistered(String email) {
		if (!userDao.getRegisteredUsers().isEmpty()) {
			for (User user : userDao.getRegisteredUsers()) {
				if (user.getEmail().equals(email))
					return true;
			}
		}
		return false;
	}
}
