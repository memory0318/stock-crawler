package tw.com.stock_crawler.util;

import org.joda.time.DateTime;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;

public class DateUtils {

	public static DateTime convertMinguoDateToJodaDateTime(String str) {
		if (str == null) {
			throw new RuntimeException("The string of Minguo date must not be null.");
		}
		// ----- -----
		String[] arr = str.trim().split("/");
		MinguoDate minguoDate = MinguoDate.of(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
		LocalDate localDate = LocalDate.from(minguoDate);
		return new DateTime(
				localDate.getYear(),
				localDate.getMonthValue(),
				localDate.getDayOfMonth(),
				0,
				0
		);
	}

	public static DateTime convertMinguoDateToJodaDateTime(MinguoDate minguoDate) {
		if (minguoDate == null) {
			throw new RuntimeException("Minguo date must not be null.");
		}
		// ----- -----
		LocalDate localDate = LocalDate.from(minguoDate);
		return new DateTime(
				localDate.getYear(),
				localDate.getMonthValue(),
				localDate.getDayOfMonth(),
				0,
				0
		);
	}
}
