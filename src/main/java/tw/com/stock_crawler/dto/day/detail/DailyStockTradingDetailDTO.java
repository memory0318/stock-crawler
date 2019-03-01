package tw.com.stock_crawler.dto.day.detail;

import java.math.BigDecimal;

public interface DailyStockTradingDetailDTO {

	String getSecurityCode();

	String getSecurityName();

	long getTradeVolume();

	long getTradeValue();

	BigDecimal getOpeningPrice();

	BigDecimal getHighestPrice();

	BigDecimal getLowestPrice();

	BigDecimal getClosingPrice();

	BigDecimal getChange();

	long getTransaction();
}
