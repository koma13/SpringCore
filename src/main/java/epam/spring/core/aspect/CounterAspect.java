package epam.spring.core.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Event;
import epam.spring.core.dao.AspectCounterDao;

@Aspect
@Component
public class CounterAspect {
	
	private AspectCounterDao aspectCounterDao;

	public AspectCounterDao getAspectCounterDao() {
		return aspectCounterDao;
	}

	@Autowired
	public void setAspectCounterDao(AspectCounterDao aspectCounterDao) {
		this.aspectCounterDao = aspectCounterDao;
	}

	public static Map<String, Integer> eventAccessCounter = new HashMap<String, Integer>();
	public static Map<Event, Integer> eventPriceAccessCounter = new HashMap<Event, Integer>();
	public static Map<String, Integer> eventTicketBookingCounter = new HashMap<String, Integer>();

	@Around(value = "execution(* epam.spring.core.dao.impl.*.getEventByName(..))")
	public Object countEventAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		String eventName = (String) args[0];
		aspectCounterDao.addAccessByName(eventName);
		return joinPoint.proceed(args);
	}

	@Around(value = "execution(* epam.spring.core.service.impl.*.getTicketPrice(..))")
	public Object countEventPriceAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Event event = (Event) args[0];
		aspectCounterDao.addAccessByPrice(event.getName());
		return joinPoint.proceed(args);
	}

	@Around(value = "execution(* epam.spring.core.service.impl.*.bookTicket(..))")
	public void countTicketsByEventBooking(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Event event = (Event) args[2];
		aspectCounterDao.addTicketBooking(event.getName());
		joinPoint.proceed(args);
	}
}
