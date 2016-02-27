package epam.spring.core.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.spring.core.bean.User;
import epam.spring.core.dao.AspectDiscountDao;

public class AspectDiscountDaoImpl implements AspectDiscountDao{
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addDiscountForUser(int userId, String eventName, int discount) {
		String insertSql = "INSERT INTO DISCOUNT_ASPECT (USER_ID, EVENT_NAME, DISCOUNT) VALUES(?,?,?);";
		getJdbcTemplate().update(insertSql, new Object[] { userId, eventName, discount });
	}	
	
	public int getDiscountCountByUser(User user) {
		int discountByUser;
		try {
			discountByUser = getJdbcTemplate().queryForObject("select count(*) from DISCOUNT_ASPECT where USER_ID = ?", new Object[] { user.getId() },Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
		return discountByUser;
	}

	@Override
	public void removeDataFromTable() {
		getJdbcTemplate().update("DELETE FROM DISCOUNT_ASPECT");
	}
}
