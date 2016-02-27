package epam.spring.core.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.AuditoriumBookingDao;
import epam.spring.core.dao.TicketBookingDao;
import epam.spring.core.dao.UserDao;
import epam.spring.core.service.BookingService;

public class BookingServiceImpl implements BookingService {

	private static final int VIP_SEAT_RAIT = 2;

	private TicketBookingDao ticketBookingDao;
	private AuditoriumBookingDao auditoriumBookingDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
	}
	@Autowired
	public void setTicketBookingDao(TicketBookingDao ticketBookingDao) {
		this.ticketBookingDao = ticketBookingDao;
	}
	@Autowired
	public void setAuditoriumBookingDao(AuditoriumBookingDao auditoriumBookingDao) {
		this.auditoriumBookingDao = auditoriumBookingDao;
	}

	public BigDecimal getTicketPrice(Event event, Date date, int seats, User user, boolean isVipSeat) {
		BigDecimal ticketPrice = event.getPrice();
		if (isVipSeat)
			ticketPrice = ticketPrice.multiply(new BigDecimal(VIP_SEAT_RAIT));
		ticketPrice = ticketPrice.multiply(new BigDecimal(seats));
		return ticketPrice;
	}

	public void bookTicket(User user, int tickets, Event event, Date date) {
		ticketBookingDao.bookTicket(user, tickets, event);
		auditoriumBookingDao.setBookedTicketsByEvent(event.getName(), date, tickets);

	}

	@Override
	public int getTicketsForEvent(Event event, Date date) {
		return auditoriumBookingDao.getBookedTicketsByEvent(event.getName(), date);
	}




}