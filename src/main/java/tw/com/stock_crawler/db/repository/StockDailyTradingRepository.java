package tw.com.stock_crawler.db.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.stock_crawler.db.entity.StockDailyTradingEntity;

import java.util.List;

public interface StockDailyTradingRepository extends JpaRepository<StockDailyTradingEntity, Long> {

	List<StockDailyTradingEntity> findBySecurityCode(String securityCode);

	List<StockDailyTradingEntity> findBySecurityCodeAndDateBetween(
			String securityCode, DateTime startDateTime, DateTime endDateTime
	);

	List<StockDailyTradingEntity> findByDate(DateTime dateTime);

	int countBySecurityCodeAndDate(String securityCode, DateTime dateTime);

	int countBySecurityCodeAndDateBetween(String securityCode, DateTime startDateTime, DateTime endDateTime);

	StockDailyTradingEntity findFirstBySecurityCodeOrderByDateAsc(String securityCode);

	StockDailyTradingEntity findFirstBySecurityCodeOrderByDateDesc(String securityCode);
}
