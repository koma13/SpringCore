package epam.spring.core.aspect;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.LuckyWinnerDao;

@Aspect
@Component
public class LuckyWinnerAspect {
	
	private LuckyWinnerDao luckyWinnerDao;

	public LuckyWinnerDao getLuckyWinnerDao() {
		return luckyWinnerDao;
	}

	@Autowired
	public void setLuckyWinnerDao(LuckyWinnerDao luckyWinnerDao) {
		this.luckyWinnerDao = luckyWinnerDao;
	}

	@Around(value = "execution(* epam.spring.core.service.impl.*.bookTicket(..))")
	public Object luckyTicketLogger(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = null;
		Random random = new Random();
		boolean randomValue;
		randomValue = random.nextBoolean();
		args = joinPoint.getArgs();
		if (randomValue) {
			User user = (User) args[0];
			Event event = (Event)args[2];
			Date date = (Date)args[3];
			event.setPrice(new BigDecimal(0));
			luckyWinnerDao.addLuckyWinnerTicket(user, event, date);
			User.logger.info("******************** " + user.getFullName() + " got lucky ticket for " + event.getName());
		}
		return joinPoint.proceed();
	}
}
