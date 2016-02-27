package epam.spring.core.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.helper.DiscountStrategy;
import epam.spring.core.service.DiscountService;

public class DiscountServiceImpl implements DiscountService {

	private List<DiscountStrategy> strategies;

	@Autowired
	public void setStrategies(List<DiscountStrategy> strategies) {
		this.strategies = strategies;
	}

	public BigDecimal getDiscount(User user, Event event, Date date) throws ParseException {
		int price = event.getPrice().intValue();
		int discountOption;
		for (DiscountStrategy strategy : strategies) {
			discountOption = strategy.getDiscountStrategy(user, event, date).intValue();
			if (discountOption < price) {
				price = discountOption;
			}
		}
		return new BigDecimal(price);

	}

}
