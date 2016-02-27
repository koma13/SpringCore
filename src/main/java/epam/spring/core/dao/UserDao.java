package epam.spring.core.dao;

import java.util.List;

import epam.spring.core.bean.User;

public interface UserDao {
	public void remove(User user);

	public User getById(int id);

	public User getUserByEmail(String email);

	public User getUserByName(String name);

	public void register(User user);

	public List<User> getRegisteredUsers();

}
