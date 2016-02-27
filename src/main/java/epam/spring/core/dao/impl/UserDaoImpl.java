package epam.spring.core.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.User;
import epam.spring.core.dao.UserDao;
import epam.spring.core.dao.rowmapper.UserRowMapper;
import epam.spring.core.helper.DateConverterUtils;

public class UserDaoImpl implements UserDao {

	public static User user1;
	public static User user2;
	public static Date date1;
	public static Date date2;
	public static Date date3;
	public static User user3;

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void init() throws ParseException {
		date1 = DateConverterUtils.getDate("25-04-2000");
		date2 = DateConverterUtils.getDate("10-04-2005");
		date3 = DateConverterUtils.getDate("15-05-2003");

		user1 = new User(1, "Ivan Golovenko", date1, "ivan_golovenko@gmail.com");
		user2 = new User(2, "Vasyl Petrenko", date2, "vasyl_petrenko@gmail.com");
		user3 = new User(3, "Vasyl Golovenko", date3, "vasyl_golovenko@gmail.com");

	}

	public List<User> getRegisteredUsers() {
		String selectAllSql = "SELECT * FROM USER;";
		return getJdbcTemplate().query(selectAllSql, new UserRowMapper());
	}

	public void register(User user) {
		String insertSql = "INSERT INTO USER (ID, FULL_NAME, EMAIL, BIRTHDAY) VALUES(?,?,?,?);";
		String name = user.getFullName();
		String email = user.getEmail();
		Date birthday = user.getBirthday();
		Integer id = user.getId();
		getJdbcTemplate().update(insertSql, new Object[] { id, name, email, birthday });
	}

	public void remove(User user) {
		getJdbcTemplate().update("DELETE FROM USER WHERE id = ?", new Object[] { user.getId() });
	}

	public User getById(int id) {
		User user;
		try {
			user = this.jdbcTemplate.queryForObject("select ID, FULL_NAME, EMAIL, BIRTHDAY from USER where ID = ?", new Object[] { id }, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	public User getUserByEmail(String email) {
		User user = this.jdbcTemplate.queryForObject("select ID, FULL_NAME, EMAIL, BIRTHDAY from USER where EMAIL = ?", new Object[] { email }, new UserRowMapper());
		return user;
	}

	public User getUserByName(String name) {
		User user = this.jdbcTemplate.queryForObject("select ID, FULL_NAME, EMAIL, BIRTHDAY from USER where FULL_NAME = ?", new Object[] { name }, new UserRowMapper());
		return user;
	}

}
