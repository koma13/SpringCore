package epam.spring.core.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;

public interface BookingService {

	public int getTicketsForEvent(Event event, Date date);

	public void bookTicket(User user, Ticket ticket);

	public BigDecimal getTicketPrice(Event event, Date date, int seats, User user, boolean isVipSeat) throws ParseException;

}
