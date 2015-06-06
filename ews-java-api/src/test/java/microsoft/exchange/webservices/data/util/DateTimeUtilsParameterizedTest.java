package microsoft.exchange.webservices.data.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
/**
 * Parametrized DateTimeUtilsTest
 *
 */
@RunWith(value = Parameterized.class)
public class DateTimeUtilsParameterizedTest {

	int fInput;
	int fExpected;
	String dateString;

	@Parameters(name= "{index}: DateString = {2} : Calendar field = {0}")
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] 
				{ { Calendar.YEAR, 2015, "2015-01-08T10:11:12Z" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12Z" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12Z" },
				  { Calendar.HOUR_OF_DAY, 10, "2015-01-08T10:11:12Z" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12Z" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12Z" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08T10:11:12z" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12z" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12z" },
				  { Calendar.HOUR_OF_DAY, 10, "2015-01-08T10:11:12z" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12z" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12z" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08T10:11:12.123Z" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12.123Z" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12.123Z" },
				  { Calendar.HOUR_OF_DAY, 10, "2015-01-08T10:11:12.123Z" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12.123Z" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12.123Z" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08T10:11:12.9999999Z" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12.9999999Z" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12.9999999Z" },
				  { Calendar.HOUR_OF_DAY, 10, "2015-01-08T10:11:12.9999999Z" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12.9999999Z" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12.9999999Z" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08T10:11:12+0200" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12+0200" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12+0200" },
				  { Calendar.HOUR_OF_DAY, 8, "2015-01-08T10:11:12+0200" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12+0200" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12+0200" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08T10:11:12" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12" },
				  { Calendar.HOUR_OF_DAY, 10, "2015-01-08T10:11:12" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08T10:11:12-02:00" }, 
				  { Calendar.MONTH, 0, "2015-01-08T10:11:12-02:00" },
				  { Calendar.DATE, 8, "2015-01-08T10:11:12-02:00" },
				  { Calendar.HOUR_OF_DAY, 12, "2015-01-08T10:11:12-02:00" },
				  { Calendar.MINUTE, 11, "2015-01-08T10:11:12-02:00" },
				  { Calendar.SECOND, 12, "2015-01-08T10:11:12-02:00" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08Z" }, 
				  { Calendar.MONTH, 0, "2015-01-08Z" },
				  { Calendar.DATE, 8, "2015-01-08Z" },
				  { Calendar.HOUR_OF_DAY, 0, "2015-01-08Z" },
				  { Calendar.MINUTE, 0, "2015-01-08Z" },
				  { Calendar.SECOND, 0, "2015-01-08Z" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08z" }, 
				  { Calendar.MONTH, 0, "2015-01-08z" },
				  { Calendar.DATE, 8, "2015-01-08z" },
				  { Calendar.HOUR_OF_DAY, 0, "2015-01-08z" },
				  { Calendar.MINUTE, 0, "2015-01-08z" },
				  { Calendar.SECOND, 0, "2015-01-08z" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08+0200" }, 
				  { Calendar.MONTH, 0, "2015-01-08+0200" },
				  { Calendar.DATE, 7, "2015-01-08+0200" },
				  { Calendar.HOUR_OF_DAY, 22, "2015-01-08+0200" },
				  { Calendar.MINUTE, 0, "2015-01-08+0200" },
				  { Calendar.SECOND, 0, "2015-01-08+0200" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08-02:00" }, 
				  { Calendar.MONTH, 0, "2015-01-08-02:00" },
				  { Calendar.DATE, 8, "2015-01-08-02:00" },
				  { Calendar.HOUR_OF_DAY, 2, "2015-01-08-02:00" },
				  { Calendar.MINUTE, 0, "2015-01-08-02:00" },
				  { Calendar.SECOND, 0, "2015-01-08-02:00" },
				  
				  { Calendar.YEAR, 2015, "2015-01-08" }, 
				  { Calendar.MONTH, 0, "2015-01-08" },
				  { Calendar.DATE, 8, "2015-01-08" },
				  { Calendar.HOUR_OF_DAY, 0, "2015-01-08" },
				  { Calendar.MINUTE, 0, "2015-01-08" },
				  { Calendar.SECOND, 0, "2015-01-08" }
				  
				};
		return Arrays.asList(data);
	}
	public DateTimeUtilsParameterizedTest(int input, int expected, String date) {
		fInput = input;
		fExpected = expected;
		dateString = date;
	}
	@Test
	public void test() {
	    Date parsed = DateTimeUtils.convertDateTimeStringToDate(dateString);
	    Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
	    calendar.setTime(parsed);
		assertEquals(fExpected, calendar.get(fInput)); 
	}
}
