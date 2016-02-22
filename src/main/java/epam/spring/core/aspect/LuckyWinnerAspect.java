package epam.spring.core.aspect;

import java.math.BigDecimal;
import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Ticket;
import epam.spring.core.bean.User;

@Aspect
@Component
public class LuckyWinnerAspect {

	@Around(value = "execution(* epam.spring.core.service.impl.*.bookTicket(..))")
	public Object luckyTicketLogger(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = null;
		Random random = new Random();
		boolean randomValue;
		randomValue = random.nextBoolean();
		args = joinPoint.getArgs();
		if (randomValue) {
			Ticket ticket = (Ticket) args[1];
			User user = (User) args[0];
			ticket.getEvent().setPrice(new BigDecimal(0));
			User.logger.info("******************** " + user.getFullName() + " got lucky ticket for " + ticket.getEvent().getName());
		}
		return joinPoint.proceed(args);
	}
}
