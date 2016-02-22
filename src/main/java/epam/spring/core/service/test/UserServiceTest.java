package epam.spring.core.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;

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
public class UserServiceTest {

	SimpleDateFormat dt = new SimpleDateFormat("yyy-MM-dd");
	private static final long USER_ID = 3L;

	@Autowired
	private UserDao userDao;

	@Test
	public void testRegisterUserServiceAddUserToList() {
		User registeredUser = UserDaoImpl.user;
		userDao.register(registeredUser);
		assertTrue("New user should be added to list of registered users", userDao.getRegisteredUsers().contains(registeredUser));
	}

	@Test
	public void testGetByIdReturnsRegisteredUser() {
		userDao.register(UserDaoImpl.user);
		User user = userDao.getById(USER_ID);
		assertEquals("Error while getting user bu id", USER_ID, user.getId());
	}



}
