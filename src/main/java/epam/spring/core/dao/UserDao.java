package epam.spring.core.dao;

import java.util.List;

import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;

public interface UserDao {
	public void remove(User user);

	public User getById(Long id);

	public User getUserByEmail(String email);

	public User getUserByName(String name);

	public void register(User user);

	public List<User> getRegisteredUsers();

	public List<Ticket> getBookedTickets(User user);
}
