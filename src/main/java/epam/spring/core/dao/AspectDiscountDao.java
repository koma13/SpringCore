package epam.spring.core.dao;

import epam.spring.core.bean.User;

public interface AspectDiscountDao {

	public void addDiscountForUser(int userId, String eventName, int discount);

	public int getDiscountCountByUser(User user);

	void removeDataFromTable();

}
