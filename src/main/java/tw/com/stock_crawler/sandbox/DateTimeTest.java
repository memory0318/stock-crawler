package tw.com.stock_crawler.sandbox;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;

public class DateTimeTest {

	public static void main(String[] args) {
		String dateStr = "88/05/21";
		String[] arr = dateStr.split("/");

		MinguoDate minguoDate = MinguoDate.of(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
		LocalDate localDate = LocalDate.from(minguoDate);

		System.out.printf("M: %s\nL: %s\n", minguoDate, localDate);
	}
}
