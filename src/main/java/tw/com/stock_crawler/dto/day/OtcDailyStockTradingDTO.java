package tw.com.stock_crawler.dto.day;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.DateTime;
import tw.com.stock_crawler.dto.day.detail.OtcDailyStockTradingDetailDTO;
import tw.com.stock_crawler.util.json.deserializer.OtcJodaTimeDeserializer;

import java.util.List;

@JsonIgnoreProperties(value = {"iTotalRecords"})
public class OtcDailyStockTradingDTO {

	@JsonProperty(value = "reportDate")
	@JsonDeserialize(using = OtcJodaTimeDeserializer.class)
	private DateTime date;

	@JsonProperty(value = "aaData")
	private List<OtcDailyStockTradingDetailDTO> data;

	// ***** ***** ***** ***** ***** Constructors

	public OtcDailyStockTradingDTO() { }

	// ***** ***** ***** ***** ***** Getter and setter methods

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public List<OtcDailyStockTradingDetailDTO> getData() {
		return data;
	}

	public void setData(List<OtcDailyStockTradingDetailDTO> data) {
		this.data = data;
	}
}
