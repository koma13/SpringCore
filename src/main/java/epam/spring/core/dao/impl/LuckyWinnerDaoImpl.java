package epam.spring.core.dao.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.LuckyWinnerDao;

public class LuckyWinnerDaoImpl implements LuckyWinnerDao {

	public final static Logger logger = Logger.getLogger(LuckyWinnerDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addLuckyWinnerTicket(User user, Event event, Date date) {
		String insertSql = "INSERT INTO LUCKY_WINNER (USER_ID, EVENT_NAME, DATE) VALUES(?,?,?);";
		int userId = user.getId();
		String name = event.getName();
		getJdbcTemplate().update(insertSql, new Object[] { userId, name, date });
	}

	public int getAmountLuckyTicketsByUser(User user) {
		int ticketsByUser;
		try {
			ticketsByUser = getJdbcTemplate().queryForObject("select count(*) from LUCKY_WINNER where USER_ID = ?", new Object[] { user.getId() }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return ticketsByUser;
	}

	@Override
	public void removeDataFromTable() {
		getJdbcTemplate().update("DELETE FROM LUCKY_WINNER");
	}
}
