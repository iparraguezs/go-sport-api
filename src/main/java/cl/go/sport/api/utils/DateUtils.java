package cl.go.sport.api.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {
	public static final String DDMMYYYY = "ddMMyyyy";
	
	public static final String DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	
	public static final String HHMM = "HHmm";
	public static final String HH_MM = "HH:mm";
	public static final String HH_MM_SS = "HH:mm:ss";
	
	public static final String DD_MM_YYYY_SLADGE = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_HH_MM_SS_SLADGE = "dd/MM/yyyy HH:mm:ss";

	public static final String MM_DD_YYYY = "MM-dd-yyyy";
	public static final String MM_DD_YYYY_HH_MM_SS = "MM-dd-yyyy HH:mm:ss";
	public static final String MM_DD_YYYY_SLADGE = "MM/dd/yyyy";
	public static final String MM_DD_YYYY_HH_MM_SS_SLADGE = "MM/dd/yyyy HH:mm:ss";
	
	public static final String YYYYMMDD = "yyyyMMdd";
	
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static final long TIMESTAMP_FACTOR_FOR_DAYS = 24 * 60 * 60 * 1000;

	public static Date today() {
		return Calendar.getInstance().getTime();
	}

	public static Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static boolean afterToday(Timestamp date) {
		return after(date, toTimestamp(today()));
	}
	
	public static boolean afterTodayInclusive(Timestamp date) {
		return afterInclusive(date, toTimestamp(today()));
	}

	public static boolean afterToday(Date date) {
		return afterToday(toTimestamp(date));
	}

	public static boolean after(Timestamp dateA, Timestamp dateB) {
		return dateA.after(dateB);
	}

	public static boolean beforeToday(Timestamp date) {
		return before(date, toTimestamp(today()));
	}
	
	public static boolean beforeTodayInclusive(Timestamp date) {
		return beforeInclusive(date, toTimestamp(today()));
	}

	public static boolean beforeToday(Date date) {
		return beforeToday(toTimestamp(date));
	}

	public static boolean before(Timestamp dateA, Timestamp dateB) {
		return dateA.before(dateB);
	}
	
	public static boolean isEnabled(Timestamp date) {
		return Objects.isNull(date) ? true : beforeToday(date);
	}
	
	public static boolean isEnabled(Date date) {
		return isEnabled(toTimestamp(date));
	}
	
	public static String toString(Date date) {
		return toString(date, DD_MM_YYYY);
	}
	
	public static String toString(Date date, String format) {
		if(Objects.isNull(format)) throw new IllegalArgumentException("Se debe indicar el formato para transformar");
		return Objects.isNull(date) ? "" : new SimpleDateFormat(format).format(date);
	}
	
	public static Date toDate(String formattedDate) throws ParseException {
		return toDate(formattedDate, DD_MM_YYYY);
	}
	
	public static Date toDate(String formattedDate, String format) throws ParseException {
		if(Objects.isNull(format)) throw new IllegalArgumentException("Se debe indicar el formato para transformar");
		return Objects.isNull(formattedDate) ? null : new SimpleDateFormat(format).parse(formattedDate);
	}
	
	public static Date toDate(long time) {
		return new Date(time);
	}
	
	public static Date nDaysFromToday(int days) {
		return toDate(today().getTime() + applyTimestampDayFactor(days));
	}

	private static long applyTimestampDayFactor(long days) {
		return days * TIMESTAMP_FACTOR_FOR_DAYS;
	}
	
	public static Date nDaysFromDate(Date date, int days) {
		return toDate(date.getTime() + applyTimestampDayFactor(days));
	}
	
	public static Date dateOfLastWeek() {
		return nDaysFromToday(-7);
	}
	
	public static java.sql.Date sqlDate() {
		return sqlDate(today());
	}
	
	public static java.sql.Date sqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public static boolean beforeInclusive(Date dateA, Date dateB) {
		return before(dateA, dateB, true);
	}

	public static boolean beforeNotInclusive(Date dateA, Date dateB) {
		return before(dateA, dateB, false);
	}

	public static boolean afterInclusive(Date dateA, Date dateB) {
		return after(dateA, dateB, true);
	}

	public static boolean afterNotInclusive(Date dateA, Date dateB) {
		return after(dateA, dateB, false);
	}

	public static boolean before(Date dateA, Date dateB, boolean inclusive) {
		int comparedResult = dateA.compareTo(dateB);
		return inclusive ? comparedResult <= 0 : comparedResult < 0;
	}

	public static boolean after(Date dateA, Date dateB, boolean inclusive) {
		int comparedResult = dateA.compareTo(dateB);
		return inclusive ? comparedResult >= 0 : comparedResult > 0;
	}
}
