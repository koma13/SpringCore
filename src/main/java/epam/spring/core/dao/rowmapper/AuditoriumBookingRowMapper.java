package epam.spring.core.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import epam.spring.core.bean.AuditoriumBooking;

public class AuditoriumBookingRowMapper implements RowMapper<AuditoriumBooking> {

	public AuditoriumBooking mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		AuditoriumBooking auditoriumBooking = new AuditoriumBooking();
		auditoriumBooking.setEventName(resultSet.getString("EVENT_NAME"));
		auditoriumBooking.setAuditoriumName(resultSet.getString("AUDITORIUM_NAME"));
		auditoriumBooking.setBookedTime(resultSet.getDate("BOOKED_TIME"));
		auditoriumBooking.setBookedTickets(resultSet.getInt("BOOKED_TICKETS"));
		return auditoriumBooking;
	}

}
