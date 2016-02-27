package epam.spring.core.dao.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;
import epam.spring.core.dao.AuditoriumBookingDao;

public class AuditoriumBookingDaoImpl implements AuditoriumBookingDao {

	public final static Logger logger = Logger.getLogger(EventDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
		try {
			String insertSql = "INSERT INTO AUDITORIUM_BOOKING (EVENT_NAME, AUDITORIUM_NAME, BOOKED_TIME) VALUES(?,?,?);";
			String eventName = event.getName();
			String auditoriumName = auditorium.getName();
			getJdbcTemplate().update(insertSql, new Object[] { eventName, auditoriumName, date });
		} catch (DuplicateKeyException e) {
			logger.warn("Auditorium Already assigned for that time");
		}
	}

	public void setBookedTicketsByEvent(String eventName, Date date, int ticketsToBook) {
		String updateSql = "UPDATE  AUDITORIUM_BOOKING SET  BOOKED_TICKETS = ? WHERE EVENT_NAME = ? AND BOOKED_TIME = ?";
		int bookedTickets = getBookedTicketsByEvent(eventName, date);
		bookedTickets += ticketsToBook;
		getJdbcTemplate().update(updateSql, new Object[] { bookedTickets, eventName, date });
	}

	public int getBookedTicketsByEvent(String eventname, Date date) {
		int bookedTickets;
		try {
			bookedTickets = getJdbcTemplate().queryForObject("select BOOKED_TICKETS from AUDITORIUM_BOOKING where EVENT_NAME = ? AND BOOKED_TIME =?", new Object[] { eventname, date }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return bookedTickets;
	}
	
	@Override
	public void removeDataFromTable() {
		getJdbcTemplate().update("DELETE FROM AUDITORIUM_BOOKING");
	}

}
