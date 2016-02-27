package epam.spring.core.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.bean.User;
import epam.spring.core.dao.UserDao;
import epam.spring.core.dao.impl.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	User registeredUser;
	User userToRemove;

	@Before
	public void prepareTestData() {
		cleanUserTable();
		registeredUser = UserDaoImpl.user1;
		userDao.register(registeredUser);
		userToRemove = UserDaoImpl.user3;
		userDao.register(userToRemove);
	}

	@Test
	public void testRegisterUser() throws ParseException {
		User newUser = UserDaoImpl.user2;
		userDao.register(newUser);
		User user = userDao.getUserByName(newUser.getFullName());
		assertEquals("User name is not as expected", newUser.getFullName(), user.getFullName());
	}

	@Test
	public void testGetUserByName() throws ParseException {
		User user = userDao.getUserByName(registeredUser.getFullName());
		assertEquals("User name is not as expected", registeredUser.getFullName(), user.getFullName());
	}

	@Test
	public void testGetUserByEmail() throws ParseException {
		User user = userDao.getUserByEmail(registeredUser.getEmail());
		assertEquals("User name is not as expected", registeredUser.getEmail(), user.getEmail());
	}

	@Test
	public void testGetUserById() throws ParseException {
		User user = userDao.getById(registeredUser.getId());
		assertEquals("User name is not as expected", registeredUser.getId(), user.getId());
	}

	@Test
	public void testGetRegisteredUsers() throws ParseException {
		List<User> users = userDao.getRegisteredUsers();
		assertNotNull("User name is not as expected", users.size());
	}

	@Test
	public void testRemoveUser() throws ParseException {
		userDao.remove(userToRemove);
		assertNull(userDao.getById(userToRemove.getId()));
	}

	private void cleanUserTable() {
		List<User> users = userDao.getRegisteredUsers();
		for (User user : users) {
			userDao.remove(user);
		}

	}
}
