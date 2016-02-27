package epam.spring.core.dao;

import epam.spring.core.bean.AspectCounter;

public interface AspectCounterDao {

	public void addAccessByName(String eventName);

	public void addAccessByPrice(String eventName);

	public void addTicketBooking(String eventName);

	public AspectCounter getAspectCount(String name);

	public void removeDataFromTable();

}
