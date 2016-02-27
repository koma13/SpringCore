package epam.spring.core.aspect;

import java.math.BigDecimal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.dao.AspectDiscountDao;

@Aspect
@Component
public class DiscountAspect {

	private static final int _100_PERCENTAGE = 100;
	private AspectDiscountDao aspectDiscountDao;

	public AspectDiscountDao getAspectDiscountDao() {
		return aspectDiscountDao;
	}

	@Autowired
	public void setAspectDiscountDao(AspectDiscountDao aspectDiscountDao) {
		this.aspectDiscountDao = aspectDiscountDao;
	}

	@AfterReturning(value = "execution(* epam.spring.core.service.impl.*.getDiscount(..))", returning="returnVal")
	public void countDiscount(JoinPoint joinPoint, Object returnVal) throws Throwable {
		Object[] args = joinPoint.getArgs();
		User user = (User) args[0];
		Event event  = (Event)args[1];
		int eventPrice = event.getPrice().intValue();
		int priceWithDiscount = ((BigDecimal)returnVal).intValue();
		int discountInPercentage = priceWithDiscount/eventPrice*_100_PERCENTAGE;
		if(eventPrice>priceWithDiscount){
			aspectDiscountDao.addDiscountForUser(user.getId(), event.getName(), discountInPercentage);
		}
	}

}
