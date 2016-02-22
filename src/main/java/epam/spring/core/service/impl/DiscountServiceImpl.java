package epam.spring.core.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import epam.spring.core.bean.Event;
import epam.spring.core.bean.User;
import epam.spring.core.helper.DiscountStrategy;
import epam.spring.core.service.DiscountService;

@Component
public class DiscountServiceImpl implements DiscountService {

	private List<DiscountStrategy> strategies;

	public List<DiscountStrategy> getStrategies() {
		return strategies;
	}

	public void setStrategies(List<DiscountStrategy> strategies) {
		this.strategies = strategies;
	}

	public BigDecimal getDiscount(User user, Event event, Date date) throws ParseException {
		BigDecimal price = event.getPrice();
		BigDecimal discountOption;
		for (DiscountStrategy strategy : strategies) {
			discountOption = strategy.getDiscountStrategy(user, event, date);
			if (discountOption.compareTo(price) < 0)
				price = discountOption;
		}
		return price;

	}

}
