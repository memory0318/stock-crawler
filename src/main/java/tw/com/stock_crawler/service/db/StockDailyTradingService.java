package tw.com.stock_crawler.service.db;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.stock_crawler.db.entity.StockDailyTradingEntity;
import tw.com.stock_crawler.db.repository.StockDailyTradingRepository;

import java.util.List;

@Service
public class StockDailyTradingService extends BaseCrudService<StockDailyTradingEntity, Long, StockDailyTradingRepository> {

	// ----- ----- Public constants
	public static final String CLASS_NAME = StockDailyTradingService.class.getName();

	// Logger
	private final Logger logger = LoggerFactory.getLogger(StockDailyTradingService.class);

	// ***** ***** ***** ***** ***** Getter and setter methods

	@Autowired
	@Override
	public void setRepository(StockDailyTradingRepository repository) {
		this.repository = repository;
	}

	// ***** ***** ***** ***** ***** Public methods

	public List<StockDailyTradingEntity> findByDateTimeBetween(String securityCode, DateTime startDateTime, DateTime endDateTime) {
		return this.repository.findBySecurityCodeAndDateBetween(securityCode, startDateTime, endDateTime);
	}

	public List<StockDailyTradingEntity> findByDateTime(DateTime dateTime) {
		return this.repository.findByDate(dateTime);
	}

	public boolean dailyTradingExists(String securityCode, DateTime dateTime) {
		return this.repository.countBySecurityCodeAndDate(securityCode, dateTime) != 0;
	}

	public DateTime latestDateTime(String securityCode) {
		StockDailyTradingEntity entity = this.repository.findFirstBySecurityCodeOrderByDateAsc(securityCode);
		return entity != null ? entity.getDate() : null;
	}
}
