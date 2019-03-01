package tw.com.stock_crawler.dto.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import tw.com.stock_crawler.dto.history.detail.TseHistoryDailyStockTradingDetailDTO;

import java.util.List;

@JsonIgnoreProperties(value = {"stat", "date", "title", "fields", "notes"})
public class TseHistoryDailyStockTradingDTO implements HistoryDayStockTradingDTO<TseHistoryDailyStockTradingDetailDTO> {

	@JsonProperty(value = "data")
	private List<TseHistoryDailyStockTradingDetailDTO> data;

	// ***** ***** ***** ***** ***** Constructors

	public TseHistoryDailyStockTradingDTO() { }

	// ***** ***** ***** ***** ***** Getter and setter methods

	public List<TseHistoryDailyStockTradingDetailDTO> getData() {
		return data;
	}

	public void setData(List<TseHistoryDailyStockTradingDetailDTO> data) {
		this.data = data;
	}
}
