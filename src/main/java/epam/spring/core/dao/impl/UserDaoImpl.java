package epam.spring.core.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;
import epam.spring.core.dao.UserDao;
import epam.spring.core.helper.DateConverterUtils;

@Component
public class UserDaoImpl implements UserDao {

	public static List<User> registeredUsers; // make it static
	public static User user;
	public static Date date;
	public static Ticket ticket;

	static {
		user = new User();
		user.setId(3L);
		try {
			date = DateConverterUtils.getDate("25-24-2015");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setBirthday(date);
		user.setFullName("Ivan Golovenko");
		user.setEmail("ivan_golovenko@gmail.com");
		user.setBookedTickets(new ArrayList<Ticket>());
		ticket = new Ticket(EventDaoImpl.event1, date);
		registeredUsers = new CopyOnWriteArrayList <User>();

	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(List<User> registeredUsers) {
		UserDaoImpl.registeredUsers = registeredUsers;
	}

	public void register(User user) {
		if (!registeredUsers.contains(user))
			registeredUsers.add(user);
	}

	public void remove(User user) {
		if (registeredUsers.contains(user))
			registeredUsers.remove(user);
	}

	public User getById(Long id) {
		User user = new User();
		for (User regUser : registeredUsers) {
			if (regUser.getId() == id) {
				return regUser;
			}
		}
		return user;
	}

	public User getUserByEmail(String email) {
		User user = new User();
		for (User regUser : registeredUsers) {
			if (regUser.getEmail() == email) {
				return regUser;
			}
		}
		return user;
	}

	public User getUserByName(String name) {
		User user = new User();
		for (User regUser : registeredUsers) {
			if (regUser.getFullName() == name) {
				return regUser;
			}
		}
		return user;
	}

	public List<Ticket> getBookedTickets(User user) {
		return user.getBookedTickets();
	}
}
