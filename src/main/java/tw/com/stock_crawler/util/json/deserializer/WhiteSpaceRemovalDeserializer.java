package tw.com.stock_crawler.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class WhiteSpaceRemovalDeserializer extends JsonDeserializer<String> {

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		return p.getText().trim();
	}
}
