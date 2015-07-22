package de.binary101.core.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public final class TimeManager {
	
	private TimeManager(){}
	
	
	
	public static DateTime UTCunixTimestampToLocalDateTime(int unixTime) {
		return new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC).plusSeconds(unixTime).toLocalDateTime().toDateTime();
	}
	
	public static int DateUTCToUnix(DateTime datetime) {
		return (int)(datetime.minusYears(1970).minusMonths(1).minusDays(1).getMillis() / 1000);
	}
	
	public static Boolean DayOfYearNotToday (int day) {
		return DateTime.now().getDayOfYear() != day;
	}
}
