package epam.spring.core.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverterUtils {

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat birthdayFormat = new SimpleDateFormat("MM-dd");

	public static synchronized Date getDate(String dateString) throws ParseException {
		return simpleDateFormat.parse(dateString);
	}

	public static synchronized String getBirthday(Date dateString) throws ParseException {
		return birthdayFormat.format(dateString);
	}

}
