package epam.spring.core.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import epam.spring.core.bean.User;

@Aspect
@Component
public class DiscountAspect {

	public static Map<User, Integer> discountByUserCounter = new HashMap<User, Integer>();
	public static int allDiscountCounter = 0;

	@Around(value = "execution(* epam.spring.core.service.impl.*.getDiscount(..))")
	public void countDiscount(ProceedingJoinPoint joinPoint) throws Throwable {
		int counter = 1;
		Object[] args = joinPoint.getArgs();
		User user = (User) args[0];
		synchronized (discountByUserCounter) {

			if (discountByUserCounter.containsKey(user)) {
				counter = discountByUserCounter.get(user);
				discountByUserCounter.put(user, increaseCounter(counter));
			} else {
				discountByUserCounter.put(user, counter);
			}
			allDiscountCounter++;
		}
		joinPoint.proceed(args);
	}

	private int increaseCounter(int counter) {
		return counter++;
	}
}
