package epam.spring.core.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.Ticket;

@Aspect
@Component
public class CounterAspect {

	private static final int ONE = 1;
	public static Map<String, Integer> eventAccessCounter = new HashMap<String, Integer>();
	public static Map<Event, Integer> eventPriceAccessCounter = new HashMap<Event, Integer>();
	public static Map<String, Integer> eventTicketBookingCounter = new HashMap<String, Integer>();

	@Around(value = "execution(* epam.spring.core.dao.impl.*.getEventByName(..))")
	public Object countEventAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		int counter = ONE;
		Object[] args = joinPoint.getArgs();
		String eventName = (String) args[0];
		synchronized (eventAccessCounter) {
			if (eventAccessCounter.containsKey(eventName)) {
				counter = eventPriceAccessCounter.get(eventName);
				eventAccessCounter.put(eventName, increaseCounter(counter));
			} else {
				eventAccessCounter.put(eventName, counter);
			}
		}
		return joinPoint.proceed(args);
	}

	@Around(value = "execution(* epam.spring.core.service.impl.*.getTicketPrice(..))")
	public Object countEventPriceAccess(ProceedingJoinPoint joinPoint) throws Throwable {
		int counter = ONE;
		Object[] args = joinPoint.getArgs();
		Event event = (Event) args[0];
		synchronized (eventPriceAccessCounter) {

			if (eventPriceAccessCounter.containsKey(event)) {
				counter = eventPriceAccessCounter.get(event);
				eventPriceAccessCounter.put(event, increaseCounter(counter));
			} else
				eventPriceAccessCounter.put(event, counter);

		}
		return joinPoint.proceed(args);
	}

	private int increaseCounter(int counter) {
		return counter++;
	}

	@Around(value = "execution(* epam.spring.core.service.impl.*.bookTicket(..))")
	public void countTicketsByEventBooking(ProceedingJoinPoint joinPoint) throws Throwable {
		int counter = ONE;
		Object[] args = joinPoint.getArgs();
		Ticket ticket = (Ticket) args[ONE];
		Event event = ticket.getEvent();
		synchronized (eventTicketBookingCounter) {
			if (eventTicketBookingCounter.containsKey(event)) {
				counter = eventTicketBookingCounter.get(event);
				eventTicketBookingCounter.put(event.getName(), increaseCounter(counter));
			} else {
				eventTicketBookingCounter.put(event.getName(), counter);
			}
		}
		joinPoint.proceed(args);
	}
}
