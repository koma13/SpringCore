package epam.spring.core.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import epam.spring.core.bean.AspectCounter;

public class AspectCounterRowMapper implements RowMapper<AspectCounter> {

	public AspectCounter mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		AspectCounter aspectCounter = new AspectCounter();
		aspectCounter.setEventName(resultSet.getString("EVENT_NAME"));
		aspectCounter.setCounterAccesseventByName(resultSet.getInt("ACCESS_BY_NAME"));
		aspectCounter.setCounterEventPrice(resultSet.getInt("ACCESS_BY_PRICE"));
		aspectCounter.setCounterTicketBooking(resultSet.getInt("BOOKED_TICKETs"));
		return aspectCounter;
	}

}
