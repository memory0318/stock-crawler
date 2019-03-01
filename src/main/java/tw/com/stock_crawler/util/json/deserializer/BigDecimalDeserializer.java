package tw.com.stock_crawler.util.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

	// ***** ***** ***** ***** ***** Constructors

	// ***** ***** ***** ***** ***** Getter and setter methods

	// ***** ***** ***** ***** ***** Public methods

	@Override
	public BigDecimal deserialize(JsonParser p, DeserializationContext ctx) throws IOException, JsonProcessingException {
		try {
			String text = p.getText().trim();
			if (text.equals("----") || text.equals("---") || text.equals("--")) {
				text = "0";
			}
			return new BigDecimal(text.replaceAll("[X]?[,]?", ""));
		} catch (Exception e) {
			e.printStackTrace();
			String errMsg = String.format("Exception occurred when deserializing text to BigDecimal (%s)", p.getText());
			throw new RuntimeException(errMsg, e);
		}
	}
}
