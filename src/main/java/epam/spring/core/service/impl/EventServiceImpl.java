package epam.spring.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;
import epam.spring.core.dao.EventDao;
import epam.spring.core.service.EventService;

@Component
@Scope("singelton")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
		List<Date> bookedAuditoriumTime = auditorium.getBoookedTime();
		for (Date bookedDate : bookedAuditoriumTime) {
			if (!bookedDate.equals(date))
				auditorium.setBoookedTime(bookedDate);
			eventDao.getEventsToAuditorium().put(event, auditorium);
		}
	}
}
