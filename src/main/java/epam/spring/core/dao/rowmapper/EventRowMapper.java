package epam.spring.core.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.Event.Raiting;

public class EventRowMapper implements RowMapper<Event> {

		public Event mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			Event event = new Event();
			event.setName(resultSet.getString("NAME"));
			event.setPrice(resultSet.getBigDecimal("BASE_PRICE"));
			event.setRaiting(Raiting.valueOf(resultSet.getString("RAITING")));
			return event;
	}

}
