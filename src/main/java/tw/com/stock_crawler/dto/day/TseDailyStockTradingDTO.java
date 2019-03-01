package tw.com.stock_crawler.dto.day;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.DateTime;
import tw.com.stock_crawler.dto.day.detail.TseDailyStockTradingDetailDTO;
import tw.com.stock_crawler.util.json.deserializer.TseJodaTimeDeserializer;

import java.util.List;

@JsonIgnoreProperties(value = {"stat", "title", "fields", "notes"})
public class TseDailyStockTradingDTO {

	@JsonProperty(value = "date")
	@JsonDeserialize(using = TseJodaTimeDeserializer.class)
	private DateTime date;

	@JsonProperty(value = "data")
	private List<TseDailyStockTradingDetailDTO> data;

	// ***** ***** ***** ***** ***** Constructors

	public TseDailyStockTradingDTO() { }

	// ***** ***** ***** ***** ***** Public methods

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public List<TseDailyStockTradingDetailDTO> getData() {
		return data;
	}

	public void setData(List<TseDailyStockTradingDetailDTO> data) {
		this.data = data;
	}
}
