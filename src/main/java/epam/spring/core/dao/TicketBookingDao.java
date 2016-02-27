package epam.spring.core.dao;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;

public interface TicketBookingDao {
	
	public void bookTicket(User user, int tickets, Event event);

	public int getBookedTicketsAmountByUser(User user);

	void removeDataFromTable();
}
