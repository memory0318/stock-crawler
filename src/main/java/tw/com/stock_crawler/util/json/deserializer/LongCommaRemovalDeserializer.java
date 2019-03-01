package tw.com.stock_crawler.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LongCommaRemovalDeserializer extends JsonDeserializer<Long> {

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return Long.parseLong(p.getText().replaceAll("[X]?[,]?", ""));
	}
}
