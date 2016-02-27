package epam.spring.core.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.TicketBookingDao;

public class TicketBookingDaoImpl implements TicketBookingDao {

	public final static Logger logger = Logger.getLogger(EventDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void bookTicket(User user, int tickets, Event event) {
		try {
			String insertSql = "INSERT INTO TICKET_BOOKING (USER_ID, BOOKED_TICKETS , EVENT_NAME) VALUES(?,?,?);";
			int userId = user.getId();
			String eventName = event.getName();
			getJdbcTemplate().update(insertSql, new Object[] { userId, tickets, eventName });
		} catch (DuplicateKeyException e) {
			logger.warn("User Already Exist");
		}
	}

	@Override
	public int getBookedTicketsAmountByUser(User user) {
		String sql = "select BOOKED_TICKETS FROM TICKET_BOOKING WHERE USER_ID = ?";
		int ticketsAmount = 0;
		try {
			ticketsAmount = getJdbcTemplate().queryForObject(sql, new Object[] { user.getId() }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.info("There is not purchased tickets by that user");
		}
		return ticketsAmount;
	}
	
	@Override
	public void removeDataFromTable() {
		getJdbcTemplate().update("DELETE FROM TICKET_BOOKING");
	}
	
}
