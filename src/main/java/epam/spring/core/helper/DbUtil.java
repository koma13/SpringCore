package epam.spring.core.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbUtil {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void initialize() {
		DataSource dataSource = getDataSource();
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS USER (ID VARCHAR(10) NOT NULL PRIMARY KEY,FULL_NAME VARCHAR(100),EMAIL VARCHAR(100), BIRTHDAY DATE)");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS EVENT (NAME VARCHAR(50)NOT NULL PRIMARY KEY, BASE_PRICE VARCHAR(100) NOT NULL, RAITING VARCHAR(10) NOT NULL)");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS AUDITORIUM (NAME VARCHAR(30),SEATS VARCHAR(200) , VIP_SEATS VARCHAR(200))");
			statement
					.executeUpdate("CREATE TABLE IF NOT EXISTS COUNTER_ASPECT (EVENT_NAME VARCHAR(50) NOT NULL PRIMARY KEY, ACCESS_BY_NAME VARCHAR(20) , ACCESS_BY_PRICE VARCHAR(20), BOOKED_TICKETS VARCHAR(20))");
			statement
			.executeUpdate("CREATE TABLE IF NOT EXISTS AUDITORIUM_BOOKING (EVENT_NAME VARCHAR(50) NOT NULL , AUDITORIUM_NAME VARCHAR(30) , BOOKED_TIME DATE NOT NULL PRIMARY KEY, BOOKED_TICKETS VARCHAR(20))");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS TICKET_BOOKING (USER_ID VARCHAR(10) NOT NULL , BOOKED_TICKETS VARCHAR(20) , EVENT_NAME VARCHAR(20))");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS LUCKY_WINNER (USER_ID VARCHAR(10) NOT NULL , EVENT_NAME VARCHAR(50) , DATE DATE)");
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS DISCOUNT_ASPECT (USER_ID VARCHAR(10) NOT NULL , EVENT_NAME VARCHAR(50) , DISCOUNT VARCHAR(3))");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
