package tw.com.stock_crawler.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class JodaTimeSerializer extends StdSerializer<DateTime> {

	// ----- ----- Public methods
	public static final String DEFAULT_FORMAT = "yyyyMMdd";

	private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DEFAULT_FORMAT);

	// ***** ***** ***** ***** ***** Constructors

	protected JodaTimeSerializer(Class<DateTime> t) {
		super(t);
	}

	protected JodaTimeSerializer(JavaType type) {
		super(type);
	}

	protected JodaTimeSerializer(Class<?> t, boolean dummy) {
		super(t, dummy);
	}

	protected JodaTimeSerializer(StdSerializer<?> src) {
		super(src);
	}

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public void serialize(DateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(this.dateTimeFormatter.print(value));
	}
}
