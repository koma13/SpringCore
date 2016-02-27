package epam.spring.core.dao.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.AspectCounter;
import epam.spring.core.dao.AspectCounterDao;
import epam.spring.core.dao.rowmapper.AspectCounterRowMapper;

public class AspectCounterDaoImpl implements AspectCounterDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addAccessByName(String eventName) {
		int aspectCounterBy = getAspectCounterByName(eventName);
		String accessByName = "ACCESS_BY_NAME";
		addAccessCounter(eventName, accessByName, aspectCounterBy);
	}

	public void addAccessByPrice(String eventName) {
		int aspectCounterBy = getAspectCounterByPrice(eventName);
		String accessByPrice = "ACCESS_BY_PRICE";
		addAccessCounter(eventName, accessByPrice, aspectCounterBy);
	}

	public void addTicketBooking(String eventName) {
		int aspectCounterBy = getAspectCounterByBookedTickets(eventName);

		String accessTicketBooking = "BOOKED_TICKETS";
		addAccessCounter(eventName, accessTicketBooking, aspectCounterBy);
	}

	public List<AspectCounter> getAllEventsAccessInfo() {
		String selectAllSql = "SELECT * FROM COUNTER_ASPECT;";
		return getJdbcTemplate().query(selectAllSql, new AspectCounterRowMapper());
	}

	private boolean isEventCounterExists(String eventName) {
		String sql = "select count(*) from COUNTER_ASPECT where EVENT_NAME = ?";
		boolean result = false;
		int count = getJdbcTemplate().queryForObject(sql, new Object[] { eventName }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}

	public AspectCounter getAspectCount(String eventName) {
		String select = "select * from COUNTER_ASPECT where EVENT_NAME = ?";
		AspectCounter aspectCounter;
		try {
			aspectCounter = this.jdbcTemplate.queryForObject(select, new Object[] { eventName }, new AspectCounterRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return aspectCounter;
	}

	private void addAccessCounter(String eventName, String accessBy, int aspectCounterBy) {
		String insertSql;
		if (isEventCounterExists(eventName)) {
			insertSql = "UPDATE  COUNTER_ASPECT SET " + accessBy + " = ? WHERE EVENT_NAME = ?";
			aspectCounterBy++;
			getJdbcTemplate().update(insertSql, new Object[] { aspectCounterBy, eventName });
		} else {
			insertSql = "INSERT INTO COUNTER_ASPECT (EVENT_NAME, " + accessBy + ") VALUES(?,?);";
			getJdbcTemplate().update(insertSql, new Object[] { eventName, "1" });
		}
	}

	private int getAspectCounterByName(String eventName) {
		AspectCounter aspectCount = getAspectCount(eventName);
		if (aspectCount == null)
			return 0;
		int aspectCounterByName = getAspectCount(eventName).getCounterAccesseventByName();
		return aspectCounterByName;
	}

	private int getAspectCounterByPrice(String eventName) {
		AspectCounter aspectCount = getAspectCount(eventName);
		if (aspectCount == null)
			return 0;
		int aspectCounterByPrice = aspectCount.getCounterEventPrice();
		return aspectCounterByPrice;
	}

	private int getAspectCounterByBookedTickets(String eventName) {
		AspectCounter aspectCount = getAspectCount(eventName);
		if (aspectCount == null)
			return 0;
		int aspectCounterByBookedTickets = getAspectCount(eventName).getCounterTicketBooking();
		return aspectCounterByBookedTickets;
	}

	public void removeDataFromTable() {
		getJdbcTemplate().update("DELETE FROM COUNTER_ASPECT");
	}

}
