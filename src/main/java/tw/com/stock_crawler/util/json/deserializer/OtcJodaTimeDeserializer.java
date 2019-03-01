package tw.com.stock_crawler.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class OtcJodaTimeDeserializer extends JsonDeserializer<DateTime> {

	// ----- ----- Public methods
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMdd";

	private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DEFAULT_DATE_TIME_FORMAT);

	// ***** ***** ***** ***** ***** Getter and setter methods

	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String regex = "[0-9]*/[0-9]{1,2}";
		String str = p.getText().trim().replaceAll("[X]?[,]?[＊]?", "");
		String[] splitStr = str.split("/");
		String yearStr = splitStr[0];
		String monthStr = splitStr[1];
		String dayStr = splitStr[2];
		long year = Long.parseLong(yearStr) + 1911L;
		String dateTimeStr = String.format("%4d%s%s", year, monthStr, dayStr);
		return this.dateTimeFormatter.parseDateTime(dateTimeStr);
	}
}
