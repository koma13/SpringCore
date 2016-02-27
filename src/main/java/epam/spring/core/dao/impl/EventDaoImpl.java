package epam.spring.core.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.Event;
import epam.spring.core.dao.EventDao;
import epam.spring.core.dao.rowmapper.EventRowMapper;

public class EventDaoImpl implements EventDao {

	public final static Logger logger = Logger.getLogger(EventDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static Event event1;
	public static Event event2;
	public static Event event3;


	public void init() {
		event1 = new Event("Concert OE", new BigDecimal(500), Event.Raiting.LOW);
		event2 = new Event("Film XX", new BigDecimal(56), Event.Raiting.HIGH);
		event3 = new Event("Balet", new BigDecimal(300), Event.Raiting.MEDIUM);

	}

	public void create(Event event) {
		try {
			String insertSql = "INSERT INTO EVENT (NAME, BASE_PRICE, RAITING) VALUES(?,?,?);";
			String name = event.getName();
			BigDecimal price = event.getPrice();
			String raiting = event.getRaiting().toString();
			getJdbcTemplate().update(insertSql, new Object[] { name, price, raiting });
		} catch (DuplicateKeyException e) {
			logger.warn("Event Already Exist");
		}
	}

	public void remove(Event event) {
		getJdbcTemplate().update("DELETE FROM EVENT WHERE NAME = ?", new Object[] { event.getName() });
	}

	public Event getEventByName(String name) {
		Event event;
		try {
			event = this.jdbcTemplate.queryForObject("select NAME, BASE_PRICE, RAITING from EVENT where NAME = ?", new Object[] { name }, new EventRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return event;
	}

	public List<Event> getAllEvents() {
		String selectAllSql = "SELECT * FROM EVENT;";
		return getJdbcTemplate().query(selectAllSql, new EventRowMapper());
	}

}
