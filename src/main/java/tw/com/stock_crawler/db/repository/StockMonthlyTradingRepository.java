package tw.com.stock_crawler.db.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.stock_crawler.db.entity.StockDailyTradingEntity;
import tw.com.stock_crawler.db.entity.StockMonthlyTradingEntity;

import java.util.List;

public interface StockMonthlyTradingRepository extends JpaRepository<StockMonthlyTradingEntity, Long> {

	List<StockMonthlyTradingEntity> findBySecurityCode(String securityCode);

	List<StockMonthlyTradingEntity> findBySecurityCodeAndDateBetween(
			String securityCode, DateTime startDateTime, DateTime endDateTime
	);

	// FIXME: 2018/9/11 需要修正
	List<StockMonthlyTradingEntity> findByDate(DateTime dateTime);

	// FIXME: 2018/9/11 需要修正
	int countBySecurityCodeAndDate(String securityCode, DateTime dateTime);

	// FIXME: 2018/9/11 需要修正
	int countBySecurityCodeAndDateBetween(String securityCode, DateTime startDateTime, DateTime endDateTime);

	StockMonthlyTradingEntity findFirstBySecurityCodeOrderByDateAsc(String securityCode);

	StockMonthlyTradingEntity findFirstBySecurityCodeOrderByDateDesc(String securityCode);
}
