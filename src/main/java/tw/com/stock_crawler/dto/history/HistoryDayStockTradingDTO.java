package tw.com.stock_crawler.dto.history;

import java.util.List;

public interface HistoryDayStockTradingDTO<T> {

	List<T> getData();
}
