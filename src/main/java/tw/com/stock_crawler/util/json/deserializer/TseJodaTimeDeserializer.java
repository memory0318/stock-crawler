package tw.com.stock_crawler.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class TseJodaTimeDeserializer extends JsonDeserializer<DateTime> {

	// ----- ----- Public methods
	public static final String DEFAULT_FORMAT = "yyyyMMdd";

	private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DEFAULT_FORMAT);

	// ***** ***** ***** ***** ***** Getter and setter methods

	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public DateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
		return this.dateTimeFormatter.parseDateTime(p.getText().trim().replaceAll("[X]?[,]?", ""));
	}
}
