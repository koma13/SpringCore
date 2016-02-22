package epam.spring.core.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import epam.spring.core.dao.AuditoriumDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class AuditoriumServiceTest {

	private static final int EXPECTED_AMOUNT_OF_AUDITORUIM = 3;
	private static final String AUDITORIUM_NAME = "blue";
	private static final int EXPECTED_AMOUNT_OF_VIP_SEATS = 18;
	private static final int EXPECTED_AMOUNT_OF_SEATS = 250;

	@Autowired
	private AuditoriumDao auditoriumDao;

	@Test
	public void testAuditoriumsAmountIsAsExpected() {
		int amountOfAuditoriums = auditoriumDao.getAuditoriums().size();
		assertEquals("The amount of auditoriums is not as expected", EXPECTED_AMOUNT_OF_AUDITORUIM, amountOfAuditoriums);
	}

	@Test
	public void testAuditoriumsAmountOfVipSeatsIsAsExpected() throws Exception {
		int vipSeatsAmount = auditoriumDao.getVipSeats(AUDITORIUM_NAME).size();
		assertEquals("The amount of auditoriums is not as expected", EXPECTED_AMOUNT_OF_VIP_SEATS, vipSeatsAmount);
	}

	@Test
	public void testAuditoriumsAmountOfSeatsIsAsExpected() throws Exception {
		int seatsNumber = auditoriumDao.getSeatsNumber(AUDITORIUM_NAME);
		assertEquals("The amount of auditoriums is not as expected", EXPECTED_AMOUNT_OF_SEATS, seatsNumber);
	}

}