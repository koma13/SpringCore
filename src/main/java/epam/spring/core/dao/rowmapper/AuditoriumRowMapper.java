package epam.spring.core.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import epam.spring.core.bean.Auditorium;

public class AuditoriumRowMapper implements RowMapper<Auditorium> {

	public Auditorium mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Auditorium auditorium = new Auditorium();
		auditorium.setName(resultSet.getString("NAME"));
		auditorium.setSeats(resultSet.getInt("SEATS"));
		auditorium.setVipSeats(getListOfVipSeats(resultSet.getString("VIP_SEATS")));
		return auditorium;
	}

	private List<String> getListOfVipSeats(String vipSeats) {
		return Arrays.asList(vipSeats.split(","));
	}

}
