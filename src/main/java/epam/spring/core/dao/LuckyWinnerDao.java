package epam.spring.core.dao;

import java.util.Date;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;

public interface LuckyWinnerDao {
	public void addLuckyWinnerTicket(User user, Event event, Date date);

	public int getAmountLuckyTicketsByUser(User user);

	public void removeDataFromTable();


}
