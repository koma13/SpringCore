package epam.spring.core.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import epam.spring.core.bean.User;

public class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setFullName(resultSet.getString(2));
			user.setEmail(resultSet.getString(3));
			user.setBirthday(resultSet.getDate(4));
			return user;
	}

}
