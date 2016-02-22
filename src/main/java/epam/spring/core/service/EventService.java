package epam.spring.core.service;

import java.util.Date;

import epam.spring.core.bean.Auditorium;
import epam.spring.core.bean.Event;

public interface EventService {

	public void assignAuditorium(Event event, Auditorium auditorium, Date date);

}
