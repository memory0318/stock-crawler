package tw.com.stock_crawler.dto.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import tw.com.stock_crawler.dto.history.detail.OtcHistoryDailyStockTradingDetailDTO;

import java.util.List;

@JsonIgnoreProperties(value = {"stkNo", "stkName", "showListPriceNote", "showListPriceLink", "reportDate", "iTotalRecords"})
public class OtcHistoryDailyStockTradingDTO implements HistoryDayStockTradingDTO<OtcHistoryDailyStockTradingDetailDTO> {

	@JsonProperty(value = "aaData")
	private List<OtcHistoryDailyStockTradingDetailDTO> data;

	// ***** ***** ***** ***** ***** Constructors

	public OtcHistoryDailyStockTradingDTO() { }

	// ***** ***** ***** ***** ***** Getter and setter methods

	public List<OtcHistoryDailyStockTradingDetailDTO> getData() {
		return data;
	}

	public void setData(List<OtcHistoryDailyStockTradingDetailDTO> data) {
		this.data = data;
	}
}
