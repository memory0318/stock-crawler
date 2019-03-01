package tw.com.stock_crawler.util;

import org.joda.time.DateTime;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.ListIterator;

public class Utils {

	public static String removeSpaces(String str) {
		String result = "";
		if (str != null) {
			result = str.replaceAll("　", "").replaceAll("\\s+", "");
		}
		return result;
	}

	public static String convertDateTimeToYYYYmmDD(DateTime dateTime) {
		String result = null;
		if (dateTime != null) {
			result = String.format("%4d%2d%2d", dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth());
		}
		return result;
	}

	public static <E> E findFirstElement(List<E> list) {
		E result = null;
		if (list != null) {
			ListIterator<E> listIterator = list.listIterator();
			while (listIterator.hasNext()) {
				if (!listIterator.hasPrevious()) {
					result = listIterator.next();
					break;
				}
			}
		}
		return result;
	}

	public static <E> E findLastElement(List<E> list) {
		E result = null;
		if (list != null) {
			ListIterator<E> listIterator = list.listIterator();
			while (listIterator.hasNext()) {
				result = listIterator.next();
				if (!listIterator.hasNext()) {
					break;
				}
			}
		}
		return result;
	}

	public static boolean pingHost(String host, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(host, port), timeout);
			return true;
		} catch (IOException e) {
			return false; // Either timeout or unreachable or failed DNS lookup.
		}
	}
}
